package com.matrix.link.ui.recorder

import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.matrix.link.R
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "RecorderFragment"
class RecorderFragment : Fragment(), Timer.OnTimerUpdateListener, View.OnClickListener {

    companion object {
        fun newInstance() = RecorderFragment()
    }

    private lateinit var viewModel: RecorderViewModel
    private var activityResultLauncher: ActivityResultLauncher<Array<String>>
    private var permissionRecordAudio = false
    private lateinit var mediaRecorder : MediaRecorder
    private lateinit var btnMic : ImageButton
    private lateinit var btnDelete : ImageButton
    private lateinit var btnDone : ImageButton
    private lateinit var btnList : ImageButton
    private lateinit var timer : com.matrix.link.ui.recorder.Timer
    private lateinit var tvTimer : TextView



    private var dirPath : String
    private var fileName : String
    private var isRecoding : Boolean
    private var isPaused : Boolean


    init {
        dirPath = String()
        fileName = String()
        isPaused = false
        isRecoding = false
        this.activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()) { result ->
            for( permission in result.values) {
                permissionRecordAudio = permissionRecordAudio && permission
            }

            if(!permissionRecordAudio) {
                Toast.makeText(requireContext(), "Permission not granted!", Toast.LENGTH_SHORT).show()
                return@registerForActivityResult
            }

            permissionRecordAudio = true
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recorder, container, false)

        timer = com.matrix.link.ui.recorder.Timer(this)
        tvTimer = view.findViewById<TextView>(R.id.timer)

        btnMic = view.findViewById<ImageButton>(R.id.btnMic)

        btnDelete = view.findViewById<ImageButton>(R.id.btnDelete)

        btnDone = view.findViewById<ImageButton>(R.id.btnDone)
        btnList = view.findViewById<ImageButton>(R.id.btnList)

        btnMic.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
        btnDone.setOnClickListener(this)
        btnList.setOnClickListener(this)

        btnDelete.isClickable = false

        return view
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun pauseRecorder() {
        mediaRecorder.pause()
        isPaused = true
        isRecoding = false
        timer.pause()
        btnMic.setImageResource(R.drawable.ic_recorder_mic_icon)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun resumeRecorder() {
        mediaRecorder.resume()
        isPaused = false
        isRecoding = true
        timer.start()
        btnMic.setImageResource(R.drawable.ic_recorder_pause_icon)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun stopRecorder() {
        timer.stop()
        mediaRecorder.apply {
            stop()
            release()
        }
        isPaused = false
        isRecoding = false

        btnList.visibility = View.VISIBLE
        btnDone.visibility = View.GONE

        btnDelete.isClickable = false
        btnDelete.setImageResource(R.drawable.ic_recorder_delete_disabled_icon)

        btnMic.setImageResource(R.drawable.ic_recorder_mic_icon)
        tvTimer.text = "00:00:00"
    }

    private fun startRecorder(){
//        if(!permissionRecordAudio){
//            activityResultLauncher.launch(arrayOf(AppPermissions.PERMISSION_RECORD_AUDIO))
//            return
//        }

        mediaRecorder = MediaRecorder()
        mediaRecorder.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(getRecordingFilePath())
            try {
                prepare()
                start()
            }catch (ex: IOException){
                Log.e(TAG, "Error initializing the MediaRecorder")
            }
            timer.start()
            btnMic.setImageResource(R.drawable.ic_recorder_pause_icon)
            isRecoding = true
            isPaused = false

            btnDelete.isClickable = true
            btnDelete.setImageResource(R.drawable.ic_recorder_delete_icon)

            btnList.visibility = View.GONE
            btnDone.visibility = View.VISIBLE


        }
    }

    private fun getRecordingFilePath(name: String? = null) : String{
        val date = SimpleDateFormat(/* pattern = */ "yyyy.MM.DD_hh.mm.ss").format(Date())
        return StringBuilder()
            .append("${activity?.externalCacheDir?.absolutePath}/")
            .append(name?: "audio_recorder")
            .append("_")
            .append(date)
            .append(".mp3")
            .toString()
    }

    override fun onTimerUpdate(duration: String) {
        requireActivity().runOnUiThread(Runnable {
            tvTimer.text = duration
        })

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnMic -> {
                when{
                    isPaused -> resumeRecorder()
                    isRecoding -> pauseRecorder()
                    else -> startRecorder()
                }
            }

            R.id.btnDelete  -> {
                stopRecorder()
                Toast.makeText(requireContext(), "Record Deleted", Toast.LENGTH_SHORT).show()

            }

            R.id.btnDone  -> {
                stopRecorder()
                Toast.makeText(requireContext(), "Record Saved", Toast.LENGTH_SHORT).show()
            }

            R.id.btnList  -> {
                Toast.makeText(requireContext(), "List Saved", Toast.LENGTH_SHORT).show()
            }

        }
    }

}