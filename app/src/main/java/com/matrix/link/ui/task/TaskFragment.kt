package com.matrix.link.ui.task

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.matrix.link.R
import com.matrix.link.network.model.Task
import com.matrix.link.network.model.TaskStatus
import java.time.Instant

private const val TAG = "TaskFragment"

class TaskFragment : Fragment() {

    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private lateinit var expandableListView : ExpandableListView
    private lateinit var expandableListAdapter: ExpandableListAdapter
    private lateinit var expandableListParent: List<Pair<TaskStatus,Int>>
    private lateinit var expandableListChild: HashMap<TaskStatus, List<Task>>
    private lateinit var fabAddTask : ExtendedFloatingActionButton
    private lateinit var taskBottomSheet : TaskBottomSheetFragment

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        setUpExpandableListView(view)

        setupBottomSheetLayout(view)

        setupSwipeRefreshLayout(view)

        setUpExtendedFloatingActionButton(view)

        return view;
    }


    @SuppressLint("NewApi")
    private fun setupBottomSheetLayout(view: View) {
        taskBottomSheet = TaskBottomSheetFragment(
            title = "Sample Title",
            description = "Sample Description",
            notes = "Sample Notes",
            user = "Sample User",
            status = TaskStatus.PENDING,
            isPriority = true,
            tag = "Sample Tag",
            createdBy = "Sample Creator",
            updatedBy = "Sample Updater",
            completeDate = null,
            createDate = Instant.now(),
            updateDate = Instant.now()
        )
    }

    private fun setupSwipeRefreshLayout(view : View){
        swipeRefreshLayout = view.findViewById(R.id.swipe_container)
        swipeRefreshLayout.isEnabled = true
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpExpandableListView(view: View){
        expandableListView = view.findViewById(R.id.expandableListView) as ExpandableListView
        expandableListChild = ExpandableListDataPump.getChildData()
        expandableListParent = ExpandableListDataPump.getParentData()
        expandableListAdapter =
            ExpandableTaskFeedAdapter(requireContext(), expandableListParent, expandableListChild)
        expandableListView.setAdapter(expandableListAdapter)

        expandableListView.setOnGroupExpandListener { groupPosition ->
            Toast.makeText(requireContext(), expandableListParent[groupPosition].first.status + " List Expanded.", Toast.LENGTH_SHORT).show()
        }

        expandableListView.setOnGroupCollapseListener {groupPosition ->
            Toast.makeText(requireContext(), expandableListParent[groupPosition].first.status + " List Collapsed.", Toast.LENGTH_SHORT).show()
        }

        expandableListView.setOnChildClickListener { parent , v, groupPosition, childPosition, id ->
            Toast.makeText(requireContext(), expandableListChild[expandableListParent[groupPosition].first]?.get(childPosition)?.title + " Clicked.", Toast.LENGTH_SHORT).show()
            false
        }

        expandableListView.expandGroup(0)
    }

    fun setUpExtendedFloatingActionButton(view : View){
        fabAddTask = view.findViewById<ExtendedFloatingActionButton>(R.id.fab_add_task)
        fabAddTask.setOnClickListener {
                view ->
//            Snackbar.make(view, "Add a Task clicked!", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .show()

            taskBottomSheet.show(requireFragmentManager(), "TaskBottomSheetFragment")
        }
    }
}