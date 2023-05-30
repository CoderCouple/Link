package com.matrix.link.ui.meeting

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matrix.link.R

class MeetingFragment : Fragment() {

    companion object {
        fun newInstance() = MeetingFragment()
    }

    private lateinit var viewModel: MeetingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meeting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MeetingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}