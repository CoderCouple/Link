package com.matrix.link.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.matrix.link.R
import com.matrix.link.network.model.TaskStatus
import java.time.Instant

class TaskBottomSheetFragment (
    private val title: String,
    private val description: String?,
    private val notes: String?,
    private val user: String,
    private val status: TaskStatus,
    private val isPriority: Boolean = false,
    private val tag: String?,
    private val createdBy: String,
    private val updatedBy: String,
    private val completeDate: Instant?,
    private val createDate: Instant,
    private val updateDate: Instant
) : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_task_bottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = title

//        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
//        descriptionTextView.text = description
//
//        val notesTextView = view.findViewById<TextView>(R.id.notesTextView)
//        notesTextView.text = notes
//
//        val userTextView = view.findViewById<TextView>(R.id.userTextView)
//        userTextView.text = user
//
//        val statusTextView = view.findViewById<TextView>(R.id.statusTextView)
//        statusTextView.text = status.toString()
//
//        val priorityTextView = view.findViewById<TextView>(R.id.priorityTextView)
//        priorityTextView.text = isPriority.toString()
//
//        val tagTextView = view.findViewById<TextView>(R.id.tagTextView)
//        tagTextView.text = tag
//
//        val createdByTextView = view.findViewById<TextView>(R.id.createdByTextView)
//        createdByTextView.text = createdBy
//
//        val updatedByTextView = view.findViewById<TextView>(R.id.updatedByTextView)
//        updatedByTextView.text = updatedBy
//
//        val completeDateTextView = view.findViewById<TextView>(R.id.completeDateTextView)
//        completeDateTextView.text = completeDate?.toString()
//
//        val createDateTextView = view.findViewById<TextView>(R.id.createDateTextView)
//        createDateTextView.text = createDate.toString()
//
//        val updateDateTextView = view.findViewById<TextView>(R.id.updateDateTextView)
//        updateDateTextView.text = updateDate.toString()
    }
}
