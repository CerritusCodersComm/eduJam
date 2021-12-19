package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.AcademicCalendarEventAdapter
import com.example.gdsc_hackathon.adapters.HolidaysItemAdapter
import com.example.gdsc_hackathon.dataModel.AcademicCalendarEventModel
import com.example.gdsc_hackathon.dataModel.HolidaysItemModel
import java.util.ArrayList

class HolidaysFragment : Fragment() {
    private lateinit var  recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_holidays, container, false)

        recyclerView = rootView.findViewById(R.id.eventsRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val data = ArrayList<HolidaysItemModel>()

        data.add(HolidaysItemModel(R.drawable.holiday_48px, "CHRISTMAS","25th December, 2021"))
        for (i in 2..20) {
            data.add(HolidaysItemModel(R.drawable.holiday_48px, "Item $i", "TIME: 16:00"))
        }

        val adapter = HolidaysItemAdapter(data)
        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter
        return rootView
    }

}