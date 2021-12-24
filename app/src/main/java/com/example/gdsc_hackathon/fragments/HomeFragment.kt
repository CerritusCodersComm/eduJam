package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.awaitAll
import com.example.gdsc_hackathon.adapters.RecentLectureAdapter
import com.example.gdsc_hackathon.dataModel.RecentLectureModel
import java.util.ArrayList

class HomeFragment : Fragment() {
    private lateinit var syllabusLayout: LinearLayout
    private lateinit var weeklyTimeTableLayout: LinearLayout
    private lateinit var holidayLayout: LinearLayout
    private lateinit var examTimeConstraintLayout: LinearLayout
    private lateinit var practicalLayout: LinearLayout
    private lateinit var previousYearPapersLayout: LinearLayout
    private lateinit var academicCalendarLayout: LinearLayout
    private lateinit var moreLayout: LinearLayout
    private lateinit var recyclerView: RecyclerView

    private lateinit var mAuth : FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView: View = inflater.inflate(R.layout.fragment_home, container, false)

        syllabusLayout = rootView.findViewById(R.id.syllabusLayout)
        syllabusLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.syllabusFragment)
        }

        weeklyTimeTableLayout = rootView.findViewById(R.id.weeklyTimeTableLayout)
        weeklyTimeTableLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.weeklyTimeTableFragment)
        }

        holidayLayout = rootView.findViewById(R.id.holidayLayout)
        holidayLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.holidayFragment)
        }

        examTimeConstraintLayout = rootView.findViewById(R.id.examTimeConstraintLayout)
        examTimeConstraintLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.examTimeConstraintFragment)
        }

        practicalLayout = rootView.findViewById(R.id.practicalLayout)
        practicalLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.practicalFragment)
        }

        previousYearPapersLayout = rootView.findViewById(R.id.previousYearPapersLayout)
        previousYearPapersLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.previousYearPapersFragment)
        }

        academicCalendarLayout = rootView.findViewById(R.id.academicCalendarLayout)
        academicCalendarLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.academicCalendarFragment)
        }

        moreLayout = rootView.findViewById(R.id.moreLayout)
        moreLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.moreFragment)
        }


        recyclerView = rootView.findViewById(R.id.recent_lectures_recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val lectures = ArrayList<RecentLectureModel>()

        lectures.add(RecentLectureModel(R.drawable.ic_baseline_video_camera_front_24, "MATHS","25th December, 2021", "20:00"))
        lectures.add(RecentLectureModel(R.drawable.ic_baseline_video_camera_front_24, "PHYSICS","27th December, 2021","20:00"))
        for (i in 2..20) {
            lectures.add(RecentLectureModel(R.drawable.ic_baseline_video_camera_front_24, "Item $i", "1 JAN","TIME: 16:00" ))
        }

        val adapter = RecentLectureAdapter(lectures)
        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter

        return rootView
    }
}

