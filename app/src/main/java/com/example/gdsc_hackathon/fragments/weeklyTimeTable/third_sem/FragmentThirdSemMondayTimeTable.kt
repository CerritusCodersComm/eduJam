package com.example.gdsc_hackathon.fragments.weeklyTimeTable.third_sem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdsc_hackathon.R

class FragmentThirdSemMondayTimeTable : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_third_sem_monday_time_table, container, false)

        return rootView
    }
}