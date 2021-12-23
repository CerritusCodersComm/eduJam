package com.example.gdsc_hackathon.fragments.syllabus.fourth_sem.computerGraphics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.ScrollView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.extensions.showSnackBar


class ComputerGraphicsPracticalFragment : Fragment() {

    private lateinit var parentLayout: ScrollView
    private lateinit var moduleOneLayout: RelativeLayout
    private lateinit var moduleTwoLayout: RelativeLayout
    private lateinit var moduleThreeLayout: RelativeLayout
    private lateinit var moduleFourLayout: RelativeLayout
    private lateinit var moduleFiveLayout: RelativeLayout
    private lateinit var moduleSixLayout: RelativeLayout
    private lateinit var moduleSevenLayout: RelativeLayout
    private lateinit var moduleEightLayout: RelativeLayout
    private lateinit var moduleNineLayout: RelativeLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =
            inflater.inflate(R.layout.fragment_computer_graphics_practical, container, false)

        parentLayout = rootView.findViewById(R.id.computerGraphicsPracticalParentLayout)
        moduleOneLayout = rootView.findViewById(R.id.topic_one_layout)
        moduleOneLayout.setOnClickListener {
            showSnackBar(
                requireActivity(),
                "4th Semester hasn't start, you will be notified once Resources for this semester will be live :)"
            )
        }

        moduleTwoLayout = rootView.findViewById(R.id.topic_two_layout)
        moduleTwoLayout.setOnClickListener {
            showSnackBar(
                requireActivity(),
                "4th Semester hasn't start, you will be notified once Resources for this semester will be live :)"
            )
        }

        moduleThreeLayout = rootView.findViewById(R.id.topic_three_layout)
        moduleThreeLayout.setOnClickListener {
            showSnackBar(
                requireActivity(),
                "4th Semester hasn't start, you will be notified once Resources for this semester will be live :)"
            )
        }

        moduleFourLayout = rootView.findViewById(R.id.topic_four_layout)
        moduleFourLayout.setOnClickListener {
            showSnackBar(
                requireActivity(),
                "4th Semester hasn't start, you will be notified once Resources for this semester will be live :)"
            )
        }

        moduleFiveLayout = rootView.findViewById(R.id.topic_five_layout)
        moduleFiveLayout.setOnClickListener {
            showSnackBar(
                requireActivity(),
                "4th Semester hasn't start, you will be notified once Resources for this semester will be live :)"
            )
        }

        moduleSixLayout = rootView.findViewById(R.id.topic_six_layout)
        moduleSixLayout.setOnClickListener {
            showSnackBar(
                requireActivity(),
                "4th Semester hasn't start, you will be notified once Resources for this semester will be live :)"
            )
        }

        moduleSevenLayout = rootView.findViewById(R.id.topic_seven_layout)
        moduleSevenLayout.setOnClickListener {
            showSnackBar(
                requireActivity(),
                "4th Semester hasn't start, you will be notified once Resources for this semester will be live :)"
            )
        }

        moduleEightLayout = rootView.findViewById(R.id.topic_eight_layout)
        moduleEightLayout.setOnClickListener {
            showSnackBar(
                requireActivity(),
                "4th Semester hasn't start, you will be notified once Resources for this semester will be live :)"
            )
        }

        moduleNineLayout = rootView.findViewById(R.id.topic_nine_layout)
        moduleNineLayout.setOnClickListener {
            showSnackBar(
                requireActivity(),
                "4th Semester hasn't start, you will be notified once Resources for this semester will be live :)"
            )
        }
        return rootView
    }
}