package com.matrix.link.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.matrix.link.R
import com.matrix.link.network.model.Task
import com.matrix.link.network.model.TaskStatus

private const val TAG = "TaskFragment"

class TaskFragment : Fragment() {

    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private lateinit var expandableListView : ExpandableListView
    private lateinit var expandableListAdapter: ExpandableListAdapter
    private lateinit var expandableListParent: List<Pair<TaskStatus,Int>>
    private lateinit var expandableListChild: HashMap<TaskStatus, List<Task>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

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
        //expandableListView.expandGroup(1)
//        expandableListView.setSelectedChild(1, 1, true);
//       //scroll to selected child
//        expandableListView.smoothScrollToPosition(expandableListView.getFlatListPosition(1))

        swipeRefreshLayout = view.findViewById(R.id.swipe_container)
        swipeRefreshLayout.isEnabled = true
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
        }

//        recyclerView = view.findViewById(R.id.task_list_rcv)
//        recyclerView.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(activity)
//            adapter = taskFeedAdapter
//        }

        return view;
    }

}