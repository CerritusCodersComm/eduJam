package com.example.gdsc_hackathon.fragments.syllabus.fourth_sem.operatingSystems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdsc_hackathon.R
import com.google.android.material.tabs.TabLayout

import androidx.viewpager.widget.ViewPager


class OperatingSystemsFragment : Fragment() {

    private lateinit var  operatingSystemsViewPagerAdapter: OperatingSystemsViewPagerAdapter
    private lateinit var  viewPager: ViewPager
    private lateinit var  tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_operating_systems, container, false)

        viewPager = rootView.findViewById(R.id.tab_viewpager)

        operatingSystemsViewPagerAdapter = OperatingSystemsViewPagerAdapter(childFragmentManager)

        operatingSystemsViewPagerAdapter.add(OperatingSystemsModuleOneFragment(), "MODUlE 1")
        operatingSystemsViewPagerAdapter.add(OperatingSystemsModuleTwoFragment(), "MODUlE 2")
        operatingSystemsViewPagerAdapter.add(OperatingSystemsModuleThreeFragment(), "MODUlE 3")
        operatingSystemsViewPagerAdapter.add(OperatingSystemsModuleFourFragment(), "MODUlE 4")
        operatingSystemsViewPagerAdapter.add(OperatingSystemsModuleFiveFragment(), "MODUlE 5")
        operatingSystemsViewPagerAdapter.add(OperatingSystemsModuleSixFragment(), "MODUlE 6")

        viewPager.adapter = operatingSystemsViewPagerAdapter

        tabLayout = rootView.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
        return rootView
    }
}