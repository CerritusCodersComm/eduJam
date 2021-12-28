package com.example.gdsc_hackathon.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import androidx.navigation.findNavController
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.activities.SignInActivity
import com.example.gdsc_hackathon.extensions.showSnackBar
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

class MoreFragment : Fragment() {

    private lateinit var syllabusLayout: RelativeLayout
    private lateinit var weeklyTimeTableLayout: RelativeLayout
    private lateinit var holidayLayout: RelativeLayout
    private lateinit var examTimeConstraintLayout: RelativeLayout
    private lateinit var practicalLayout: RelativeLayout
    private lateinit var previousYearPapersLayout: RelativeLayout
    private lateinit var academicCalendarLayout: RelativeLayout
    private lateinit var todolistLayout: RelativeLayout
    private lateinit var videoLecturesLayout: RelativeLayout
    private lateinit var lectureSummaryLayout: RelativeLayout
    private lateinit var logoutButton: Button

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

        todolistLayout = rootView.findViewById(R.id.todolist_layout)
        todolistLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.todoListFragment)
        }

        videoLecturesLayout = rootView.findViewById(R.id.video_lectures_layout)
        videoLecturesLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.VideoLectureFragment)
        }

        lectureSummaryLayout = rootView.findViewById(R.id.lecture_summary_layout)
        lectureSummaryLayout.setOnClickListener {
            showSnackBar(requireActivity(), "COMING SOON!")
        }

        logoutButton = rootView.findViewById(R.id.logout)
        logoutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        return rootView
    }

}