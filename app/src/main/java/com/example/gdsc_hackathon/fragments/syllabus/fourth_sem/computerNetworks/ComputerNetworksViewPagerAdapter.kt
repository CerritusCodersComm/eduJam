package com.example.gdsc_hackathon.fragments.syllabus.fourth_sem.computerNetworks

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.ArrayList


class ComputerNetworksViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    private val fragments: MutableList<Fragment> = ArrayList()
    private val fragmentTitle: MutableList<String> = ArrayList()
    fun add(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentTitle.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitle[position]
    }
}

