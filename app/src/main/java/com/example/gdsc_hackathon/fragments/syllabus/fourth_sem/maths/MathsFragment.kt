package com.example.gdsc_hackathon.fragments.syllabus.fourth_sem.maths

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdsc_hackathon.R
import com.google.android.material.tabs.TabLayout

import androidx.viewpager.widget.ViewPager


class MathsFragment : Fragment() {

    private lateinit var  mathsViewPagerAdapter: MathsViewPagerAdapter
    private lateinit var  viewPager: ViewPager
    private lateinit var  tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_maths, container, false)

        viewPager = rootView.findViewById(R.id.tab_viewpager)

        mathsViewPagerAdapter = MathsViewPagerAdapter(childFragmentManager)

        mathsViewPagerAdapter.add(MathsModuleOneFragment(), "MODUlE 1")
        mathsViewPagerAdapter.add(MathsModuleTwoFragment(), "MODUlE 2")
        mathsViewPagerAdapter.add(MathsModuleThreeFragment(), "MODUlE 3")
        mathsViewPagerAdapter.add(MathsModuleFourFragment(), "MODUlE 4")
        mathsViewPagerAdapter.add(MathsModuleFiveFragment(), "MODUlE 5")
        mathsViewPagerAdapter.add(MathsModuleSixFragment(), "MODUlE 6")

        viewPager.adapter = mathsViewPagerAdapter

        tabLayout = rootView.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
        return rootView
    }
}