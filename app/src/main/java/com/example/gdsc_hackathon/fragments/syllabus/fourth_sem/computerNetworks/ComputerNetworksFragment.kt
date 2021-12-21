package com.example.gdsc_hackathon.fragments.syllabus.fourth_sem.computerNetworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdsc_hackathon.R
import com.google.android.material.tabs.TabLayout

import androidx.viewpager.widget.ViewPager


class ComputerNetworksFragment : Fragment() {

    private lateinit var  computerNetworksViewPagerAdapter: ComputerNetworksViewPagerAdapter
    private lateinit var  viewPager: ViewPager
    private lateinit var  tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_computer_networks, container, false)

        viewPager = rootView.findViewById(R.id.tab_viewpager)

        computerNetworksViewPagerAdapter = ComputerNetworksViewPagerAdapter(childFragmentManager)

        computerNetworksViewPagerAdapter.add(ComputerNetworksModuleOneFragment(), "MODUlE 1")
        computerNetworksViewPagerAdapter.add(ComputerNetworksModuleTwoFragment(), "MODUlE 2")
        computerNetworksViewPagerAdapter.add(ComputerNetworksModuleThreeFragment(), "MODUlE 3")
        computerNetworksViewPagerAdapter.add(ComputerNetworksModuleFourFragment(), "MODUlE 4")
        computerNetworksViewPagerAdapter.add(ComputerNetworksModuleFiveFragment(), "MODUlE 5")
        computerNetworksViewPagerAdapter.add(ComputerNetworksModuleSixFragment(), "MODUlE 6")

        viewPager.adapter = computerNetworksViewPagerAdapter

        tabLayout = rootView.findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)
        return rootView
    }
}