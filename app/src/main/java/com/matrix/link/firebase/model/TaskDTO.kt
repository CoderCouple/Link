package com.matrix.link.firebase

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.matrix.link.network.model.TaskStatus
import java.time.Instant

@IgnoreExtraProperties
data class TaskDTO (
     val title : String,
     val description : String?,
     val notes: String?,
     val user: String,
     val status : TaskStatus,
     val isPriority: Boolean = false,
     val tag: String?,
     val createdBy: String,
     val updatedBy: String,
     val completeDate: Instant?,
     val createDate: Instant,
     val updateDate: Instant
)