package com.matrix.link.network.model

import java.time.Instant

data class Task (
    val title : String = "Sample Task",
    val description : String = "Creating a sample task for demo.",
    val notes: String = "Add the notes to the task over the period of time to keep track of the progress",
    val user: String = "sunildt",
    val status : TaskStatus = TaskStatus.COMPLETED,
    val isPriority: Boolean = true,
    val tag: String = "Urgent",
    val createdBy: String? = null,
    val updatedBy: String? = null,
    val completeDate: Instant? = null,
    val createDate: Instant? = null,
    val updateDate: Instant? = null){

    companion object{
        fun getSampleTask() :Task{
            return Task();
        }
    }
}

enum class TaskStatus(val status : String){
    PENDING("Pending"),
    IN_PROGRESS("InProgress"),
    COMPLETED("Completed"),
    EXPIRED("Expired");
}

enum class TaskPriority( val priority: String){
    HIGH("High"),
    LOW("Low");
}



