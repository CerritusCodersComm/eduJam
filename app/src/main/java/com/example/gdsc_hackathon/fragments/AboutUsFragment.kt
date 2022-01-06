package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.ProfileAdapter
import com.example.gdsc_hackathon.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!
    val names = listOf<String>("Dhiraj Chauhan","Tejas Borkar")
    val images = listOf<Int>(R.drawable.dhiraj_chauhan,R.drawable.tejas_borkar)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val adapter = context?.let { ProfileAdapter(names ,images , it) }
        binding.recyclerViewProfiles.layoutManager = GridLayoutManager(this.context,2)
        binding.recyclerViewProfiles.adapter = adapter

        return binding.root
    }


}