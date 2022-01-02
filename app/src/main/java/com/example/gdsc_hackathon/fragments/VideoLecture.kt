package com.example.gdsc_hackathon.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.activities.VideoLecturePlayer
import com.example.gdsc_hackathon.adapters.VideoLectureAdapter
import com.example.gdsc_hackathon.dataModel.VideoLecturesModel
import java.util.ArrayList

class VideoLecture : Fragment(), VideoLectureAdapter.OnItemClicked {

    lateinit var recyclerView: RecyclerView
    val data = ArrayList<VideoLecturesModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_video_lectures, container, false)

        recyclerView = rootView.findViewById(R.id.videoLecturesRecyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(context)

        data.add(
            VideoLecturesModel(
                "07xStMak6vI",
                "http://img.youtube.com/vi/07xStMak6vI/mqdefault.jpg",
                "DLDA",
                "MR VIKAS SINGH",
                "02 Dec"
            )
        )

        data.add(
            VideoLecturesModel(
                "n0qpnrtHS1U",
                "http://img.youtube.com/vi/n0qpnrtHS1U/mqdefault.jpg",
                "DLDA",
                "Dr Zahir Aalam",
                "28 Nov"
            )
        )

        data.add(
            VideoLecturesModel(
                "C1MJEVVPLJs",
                "http://img.youtube.com/vi/C1MJEVVPLJs/mqdefault.jpg",
                "DLDA",
                "Dr Zahir Aalam",
                "22 Nov"
            )
        )

        data.add(
            VideoLecturesModel(
                "n3n9kzqntTg",
                "http://img.youtube.com/vi/n3n9kzqntTg/mqdefault.jpg",
                "DLDA",
                "MR VIKAS SINGH",
                "21 Nov"
            )
        )

        data.add(
            VideoLecturesModel(
                "GVn5bBqVLAk",
                "http://img.youtube.com/vi/GVn5bBqVLAk/mqdefault.jpg",
                "DLDA",
                "Dr Zahir Aalam",
                "16 Nov"
            )
        )

        val adapter = VideoLectureAdapter(data)
        adapter.setOnClick(this)
        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter
        return rootView
    }

    override fun onItemClick(position: Int) {
        val videoLecturesModel = data[position]
        startActivity(
            Intent(context, VideoLecturePlayer::class.java)
            .putExtra("lectureID", videoLecturesModel.lectureId)
            .putExtra("lectureTitle", videoLecturesModel.lectureTitle)
            .putExtra("lectureTeacher", videoLecturesModel.lectureTeacher)
            .putExtra("lectureDate", videoLecturesModel.lectureDate))
    }
}