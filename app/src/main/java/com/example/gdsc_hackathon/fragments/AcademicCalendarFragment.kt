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
import com.example.gdsc_hackathon.extensions.showSnackBar

import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.android.material.button.MaterialButton
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

        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "T-Spark 2022 Selection","2 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "Starting of NGO Internships","3 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "Orientation about NGO and social Activities","3 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "Briefing of Activities / Tasks","4 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "Status of  activities assigned and  students interaction",
            "7 Jan to 13 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "ESD-Sem IV: COMP,IT, E&TC ","7 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "ESD-Sem IV: All Branches (Diploma) ","7 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "ESD-Sem IV: ELEX, MECH, CIVL,IOT, AI&DS, AI&ML "
            ,"7 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "T-Spark 2022 Day-1","10 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "T-Spark 2022 Day-2","11 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "T-Spark 2022 Day-3","12 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "T-Spark 2022 Day-4","13 Jan"))

        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "Presentation and Discussion","14 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "Ending of  NGO Internships","15 Jan"))
        data.add(AcademicCalendarEventModel(R.drawable.ic_academic_calendar_48, "Final  Evaluation and Report Submission ","15 Jan"))

        val adapter = AcademicCalendarEventAdapter(data)
        adapter.setOnClick(this)

        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter

        monthName = rootView.findViewById(R.id.academicCalendarMonthName)
        val currentDate =dateFormatForMonth.format(Date()).toString()

        monthName.text = currentDate
        // to go to previous month
        previousMonth = rootView.findViewById(R.id.previousMonth)
        previousMonth.setOnClickListener {
            compactCalendarView.scrollLeft()
        }

        // to go to next month
        nextMonth = rootView.findViewById(R.id.nextMonth)
        nextMonth.setOnClickListener {
            compactCalendarView.scrollRight()
        }

        compactCalendarView.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                val events: List<Event> = compactCalendarView.getEvents(dateClicked)
                showSnackBar(requireActivity(), dateClicked.toString())
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                monthName.text = dateFormatForMonth.format(firstDayOfNewMonth).toString()

            }
        })
        return rootView
    }

    override fun onItemClick(position: Int) {
        showSnackBar(requireActivity(), "Google Calendar integration coming soon")
    }
}