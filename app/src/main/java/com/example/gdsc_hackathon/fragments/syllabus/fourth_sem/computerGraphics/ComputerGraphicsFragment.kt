package com.example.gdsc_hackathon.fragments.syllabus.fourth_sem.computerGraphics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdsc_hackathon.R
import com.google.android.material.tabs.TabLayout

import androidx.viewpager.widget.ViewPager


class ComputerGraphicsFragment : Fragment() {

    private lateinit var  computerGraphicsViewPagerAdapter: ComputerGraphicsViewPagerAdapter
    private lateinit var  viewPager: ViewPager
    private lateinit var  tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_computer_graphics, container, false)

        viewPager = rootView.findViewById(R.id.tab_viewpager)

        computerGraphicsViewPagerAdapter = ComputerGraphicsViewPagerAdapter(childFragmentManager)

        computerGraphicsViewPagerAdapter.add(ComputerGraphicsModuleOneFragment(), "MODUlE 1")
        computerGraphicsViewPagerAdapter.add(ComputerGraphicsModuleTwoFragment(), "MODUlE 2")
        computerGraphicsViewPagerAdapter.add(ComputerGraphicsModuleThreeFragment(), "MODUlE 3")
        computerGraphicsViewPagerAdapter.add(ComputerGraphicsModuleFourFragment(), "MODUlE 4")
        computerGraphicsViewPagerAdapter.add(ComputerGraphicsModuleFiveFragment(), "MODUlE 5")
        computerGraphicsViewPagerAdapter.add(ComputerGraphicsModuleSixFragment(), "MODUlE 6")

        viewPager.adapter = computerGraphicsViewPagerAdapter

        tabLayout = rootView.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
        return rootView
    }
}