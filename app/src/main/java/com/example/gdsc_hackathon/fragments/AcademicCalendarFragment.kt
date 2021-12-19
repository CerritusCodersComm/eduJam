package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.AcademicCalendarEventAdapter
import com.example.gdsc_hackathon.dataModel.AcademicCalendarEventModel

import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class AcademicCalendarFragment : Fragment(), AcademicCalendarEventAdapter.OnItemClicked {

    private lateinit var  compactCalendarView : CompactCalendarView
    private lateinit var  previousMonth : MaterialButton
    private lateinit var  nextMonth : MaterialButton
    private lateinit var  monthName : TextView
    private lateinit var  recyclerView: RecyclerView
    private val currentCalender = Calendar.getInstance(Locale.getDefault())

    private val dateFormatForMonth: SimpleDateFormat =
        SimpleDateFormat("MMM - yy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_academic_calendar, container, false)

        compactCalendarView = rootView.findViewById(R.id.academicCalendarView)

        recyclerView = rootView.findViewById(R.id.eventsRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val data = ArrayList<AcademicCalendarEventModel>()

        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "CSI WEBINAR","TIME: 16:00"))
        for (i in 2..20) {
            data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "Item $i", "TIME: 16:00"))
        }

        val adapter = AcademicCalendarEventAdapter(data)
        adapter.setOnClick(this)

        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter
        return rootView
    }

    override fun onItemClick(position: Int) {
        Snackbar.make(requireView(),"Google Calendar integration coming soon",Snackbar.LENGTH_SHORT).show()

    }
}