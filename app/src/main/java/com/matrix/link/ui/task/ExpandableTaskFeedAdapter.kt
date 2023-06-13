package com.matrix.link.ui.task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.matrix.link.R
import com.matrix.link.network.model.Task
import com.matrix.link.network.model.TaskStatus
import com.matrix.link.util.TaskUtil


class ExpandableTaskFeedAdapter(
    context: Context,
    expandableListTitle: List<Pair<TaskStatus, Int>>,
    expandableListDetail: HashMap<TaskStatus, List<Task>>
) : BaseExpandableListAdapter() {

    private var context: Context
    private var expandableListTitle: List<Pair<TaskStatus, Int>>
    private var expandableListDetail: HashMap<TaskStatus, List<Task>>

    init {
        this.context = context
        this.expandableListTitle = expandableListTitle
        this.expandableListDetail = expandableListDetail
    }

    override fun getGroupCount(): Int {
        return this.expandableListTitle.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.expandableListDetail.get(this.expandableListTitle.get(groupPosition).first)?.size
            ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.expandableListTitle.get(groupPosition)
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.expandableListDetail.get(this.expandableListTitle.get(groupPosition).first)
            ?.get(childPosition)!!
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val listItem = getGroup(groupPosition) as Pair<TaskStatus, Int>
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.layout_task_list_parent_item, null)
        }

        //convertView?.setBackgroundColor(Color.parseColor("#FFFFFF"));
        if (isExpanded) {
            convertView?.setBackgroundColor(context.getResources().getColor(R.color.gray_light));
        } else {
            convertView?.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        val listTitleTextView =
            convertView!!.findViewById<TextView>(R.id.task_expandable_group_heading)
        //listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listItem.first.status

        val listTitleImageView =
            convertView!!.findViewById<ImageView>(R.id.task_expandable_group_image)
        listTitleImageView.setImageResource(listItem.second)

        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val task = getChild(groupPosition, childPosition) as Task
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.layout_task_list_child_item, null)
        }

        val tvTaskTitle = convertView!!.findViewById<TextView>(R.id.task_title)

        val btnTaskPriority = convertView.findViewById<ImageView>(R.id.task_priority_button)
        val tvTaskPriority = convertView.findViewById<TextView>(R.id.task_priority_text)

        val ivTaskStatus = convertView.findViewById<ImageView>(R.id.task_status_button)
        val tvTaskStatus = convertView.findViewById<TextView>(R.id.task_status_text)

        val ivTaskUser = convertView.findViewById<ImageView>(R.id.task_user_button)
        val tvTaskUser = convertView.findViewById<TextView>(R.id.task_user_text)

        tvTaskTitle.text = task.title

        tvTaskPriority.text = TaskUtil.getTaskPriorityLabel(task.isPriority).lowercase()

        tvTaskStatus.text = task.status.name.lowercase()
        ivTaskStatus.setImageResource(TaskUtil.getTaskStatusImage(task.status))

        tvTaskUser.text = task.user
        ivTaskUser.setImageResource(TaskUtil.getTaskUserProfileImage(task.user))
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}