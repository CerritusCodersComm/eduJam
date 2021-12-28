package com.example.gdsc_hackathon.fragments.weeklyTimeTable

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.fragments.weeklyTimeTable.third_sem.*
import com.google.android.material.tabs.TabLayout

class WeeklyTimeTableFragment : Fragment() {

    private lateinit var  fragmentThirdSemTimeTableAViewPagerAdapter: FragmentThirdSemTimeTableAViewPagerAdapter
    private lateinit var  viewPager: ViewPager
    private lateinit var  tabLayout: TabLayout
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_weekly_time_table, container, false)

        viewPager = rootView.findViewById(R.id.tab_viewpager)

        fragmentThirdSemTimeTableAViewPagerAdapter = FragmentThirdSemTimeTableAViewPagerAdapter(childFragmentManager)

        fragmentThirdSemTimeTableAViewPagerAdapter.add(FragmentThirdSemMondayTimeTable(), "Monday")
        fragmentThirdSemTimeTableAViewPagerAdapter.add(FragmentThirdSemTuesdayTimeTable(), "Tuesday")
        fragmentThirdSemTimeTableAViewPagerAdapter.add(FragmentThirdSemWednesdayTimeTable(), "Wednesday")
        fragmentThirdSemTimeTableAViewPagerAdapter.add(FragmentThirdSemThursdayTimeTable(), "Thursday")
        fragmentThirdSemTimeTableAViewPagerAdapter.add(FragmentThirdSemFridayTimeTable(), "Friday")

        viewPager.adapter = fragmentThirdSemTimeTableAViewPagerAdapter

        tabLayout = rootView.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
        return rootView
    }
}