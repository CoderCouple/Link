package com.matrix.link.ui.task

import android.util.Log
import com.matrix.link.R
import com.matrix.link.network.model.Task
import com.matrix.link.network.model.TaskStatus

private const val TAG = "ExpandableListDataPump"

object ExpandableListDataPump {

    fun getChildData(): HashMap<TaskStatus, List<Task>> {
        val expandableListDetail = HashMap<TaskStatus, List<Task>>()
        val completed: MutableList<Task> = ArrayList()
        completed.add(Task(status  = TaskStatus.COMPLETED))
        completed.add(Task(status  = TaskStatus.COMPLETED))
        completed.add(Task(status  = TaskStatus.COMPLETED))

        val pending: MutableList<Task> = ArrayList()
        pending.add(Task(status  = TaskStatus.PENDING))
        pending.add(Task(status  = TaskStatus.PENDING))

        val inProgress: MutableList<Task> = ArrayList()
        inProgress.add(Task(status  = TaskStatus.IN_PROGRESS))
        inProgress.add(Task(status  = TaskStatus.IN_PROGRESS))
        inProgress.add(Task(status  = TaskStatus.IN_PROGRESS))

        val expired: MutableList<Task> = ArrayList()
        expired.add(Task(status  = TaskStatus.EXPIRED))

        expandableListDetail[TaskStatus.PENDING] = pending
        expandableListDetail[TaskStatus.IN_PROGRESS] = inProgress
        expandableListDetail[TaskStatus.COMPLETED] = completed
        expandableListDetail[TaskStatus.EXPIRED] = expired

        return expandableListDetail
    }

    fun getParentData() : List<Pair<TaskStatus,Int>>{
        return listOf<Pair<TaskStatus,Int>>(
            Pair(TaskStatus.PENDING, R.drawable.ic_task_pending_icon),
            Pair(TaskStatus.IN_PROGRESS, R.drawable.ic_task_inprogress_icon),
            Pair(TaskStatus.COMPLETED, R.drawable.ic_task_completed_icon),
            Pair(TaskStatus.EXPIRED, R.drawable.ic_task_expired_icon)
        )
    }
}

