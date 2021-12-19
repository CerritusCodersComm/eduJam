package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.navigation.findNavController
import com.example.gdsc_hackathon.R
import com.google.android.material.snackbar.Snackbar

class MoreFragment : Fragment() {

    lateinit var syllabusLayout: RelativeLayout
    lateinit var weeklyTimeTableLayout: RelativeLayout
    lateinit var holidayLayout: RelativeLayout
    lateinit var examTimeConstraintLayout: RelativeLayout
    lateinit var practicalLayout: RelativeLayout
    lateinit var previousYearPapersLayout: RelativeLayout
    lateinit var academicCalendarLayout: RelativeLayout
    lateinit var videoLecturesLayout: RelativeLayout
    lateinit var lectureSummaryLayout: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView: View = inflater.inflate(R.layout.fragment_more, container, false)

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

        videoLecturesLayout = rootView.findViewById(R.id.video_lectures_layout)
        videoLecturesLayout.setOnClickListener {
            Snackbar.make(rootView, "COMING SOON!", Snackbar.LENGTH_SHORT).show()
        }

        lectureSummaryLayout = rootView.findViewById(R.id.lecture_summary_layout)
        lectureSummaryLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.lectureSummaryFragment)
        }

        return rootView
    }

}