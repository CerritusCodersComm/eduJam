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
import com.example.gdsc_hackathon.dataModel.AcademicCalendar

import com.example.gdsc_hackathon.extensions.showSnackBar
import com.firebase.ui.firestore.FirestoreRecyclerOptions

import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*


class AcademicCalendarFragment : Fragment() {

    private lateinit var  compactCalendarView : CompactCalendarView
    private lateinit var  previousMonth : MaterialButton
    private lateinit var  nextMonth : MaterialButton
    private lateinit var  monthName : TextView
    private lateinit var  recyclerView: RecyclerView
    private lateinit var  adapter: AcademicCalendarEventAdapter
    private val currentCalender = Calendar.getInstance(Locale.getDefault())
    private val reference = FirebaseFirestore.getInstance().collection("AcademicCalendar")

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

        val query = reference.orderBy("startingDate", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<AcademicCalendar>()
            .setQuery(query, AcademicCalendar::class.java).build()

         adapter = AcademicCalendarEventAdapter(options)


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

//    override fun onItemClick(position: Int) {
//        showSnackBar(requireActivity(), "Google Calendar integration coming soon")
//    }
    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}