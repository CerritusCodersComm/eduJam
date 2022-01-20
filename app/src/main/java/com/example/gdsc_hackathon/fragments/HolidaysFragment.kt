package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R

import com.example.gdsc_hackathon.adapters.HolidaysItemAdapter

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

        recyclerView = rootView.findViewById(R.id.holidaysRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val data = ArrayList<HolidaysItemModel>()

        // static for now
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Makar Sankranti","14 Jan"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Republic Day","26 Jan"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Chhatrapati Shivaji Maharaj Jayanti","19 Feb"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Maha Shivaratri/Shivaratri","1 Mar"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Holi","18 Mar"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Gudi Padwa","2 Apr"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Ambedkar Jayanti","14 Apr"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Good Friday","15 Apr"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Ramzan Eid","3 May"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Buddha Purnima","16 May"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Muharram","9 Aug"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Raksha Bandhan","9 Aug"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Muharram","9 Aug"))
        data.add(HolidaysItemModel(R.drawable.holiday_48px, "Independence Day","15 Aug"))

        val adapter = HolidaysItemAdapter(data)
        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter
        return rootView
    }

}