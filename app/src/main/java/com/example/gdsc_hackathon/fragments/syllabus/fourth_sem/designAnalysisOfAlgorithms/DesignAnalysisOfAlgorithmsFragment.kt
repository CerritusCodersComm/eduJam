package com.example.gdsc_hackathon.fragments.syllabus.fourth_sem.designAnalysisOfAlgorithms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdsc_hackathon.R
import com.google.android.material.tabs.TabLayout

import androidx.viewpager.widget.ViewPager


class DesignAnalysisOfAlgorithmsFragment : Fragment() {

    private lateinit var  designAnalysisOfAlgorithmsViewPagerAdapter: DesignAnalysisOfAlgorithmsViewPagerAdapter
    private lateinit var  viewPager: ViewPager
    private lateinit var  tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_design_analysis_of_algorithms, container, false)

        viewPager = rootView.findViewById(R.id.tab_viewpager)

        designAnalysisOfAlgorithmsViewPagerAdapter = DesignAnalysisOfAlgorithmsViewPagerAdapter(childFragmentManager)

        designAnalysisOfAlgorithmsViewPagerAdapter.add(DesignAnalysisOfAlgorithmsModuleOneFragment(), "MODUlE 1")
        designAnalysisOfAlgorithmsViewPagerAdapter.add(DesignAnalysisOfAlgorithmsModuleTwoFragment(), "MODUlE 2")
        designAnalysisOfAlgorithmsViewPagerAdapter.add(DesignAnalysisOfAlgorithmsModuleThreeFragment(), "MODUlE 3")
        designAnalysisOfAlgorithmsViewPagerAdapter.add(DesignAnalysisOfAlgorithmsModuleFourFragment(), "MODUlE 4")
        designAnalysisOfAlgorithmsViewPagerAdapter.add(DesignAnalysisOfAlgorithmsModuleFiveFragment(), "MODUlE 5")
        designAnalysisOfAlgorithmsViewPagerAdapter.add(DesignAnalysisOfAlgorithmsModuleSixFragment(), "MODUlE 6")

        viewPager.adapter = designAnalysisOfAlgorithmsViewPagerAdapter

        tabLayout = rootView.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
        return rootView
    }
}