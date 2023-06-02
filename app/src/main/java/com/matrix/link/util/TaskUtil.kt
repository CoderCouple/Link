package com.matrix.link.util

import com.matrix.link.R
import com.matrix.link.network.model.TaskPriority
import com.matrix.link.network.model.TaskStatus

object TaskUtil {

    fun getTaskStatusImage(status: TaskStatus): Int {
        when (status) {
            TaskStatus.PENDING -> return R.drawable.ic_task_pending_icon
            TaskStatus.IN_PROGRESS -> return R.drawable.ic_task_inprogress_icon
            TaskStatus.COMPLETED -> return R.drawable.ic_task_completed_icon
            TaskStatus.EXPIRED -> return R.drawable.ic_task_expired_icon
            else -> {
                return 0
            }
        }
    }


    fun getTaskUserProfileImage(user: String): Int {
        return R.drawable.ic_task_user_icon
    }

    fun getTaskTagImage(tag: String): Int {
        return R.drawable.ic_task_tag_icon
    }


    fun getTaskPriorityLabel(isPriority: Boolean): String {
        return if (isPriority) TaskPriority.HIGH.priority else TaskPriority.LOW.priority
    }
}