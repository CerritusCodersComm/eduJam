package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.AcademicCalendarEventAdapter
import com.example.gdsc_hackathon.adapters.ProfileAdapter
import com.example.gdsc_hackathon.databinding.FragmentAboutUsBinding
import com.example.gdsc_hackathon.extensions.showSnackBar
import android.content.Intent
import android.net.Uri


class AboutUsFragment : Fragment(), ProfileAdapter.OnItemClicked {

    private var _binding: FragmentAboutUsBinding? = null
    private val binding get() = _binding!!
    val names = listOf("Dhiraj Chauhan","Tejas Borkar", "Anam Ansari", "Wilfred Almeida")
    val images = listOf(R.drawable.dhiraj_chauhan,R.drawable.tejas_borkar,R.drawable.anam_ansari, R.drawable.wilfred_almeida)
    val githubIcon = listOf(R.drawable.github_icon_24,R.drawable.github_icon_24,R.drawable.github_icon_24, R.drawable.github_icon_24)
    val linkedinIcon = listOf(R.drawable.linkedin_icon_24,R.drawable.linkedin_icon_24,R.drawable.linkedin_icon_24, R.drawable.linkedin_icon_24)
    val githubID = listOf("https://www.github.com/cdhiraj40/","https://github.com/tejasvb/","https://github.com/anamansari062","https://github.com/WilfredAlmeida")
    val linkedinID = listOf("https://www.linkedin.com/in/cdhiraj40/","https://www.linkedin.com/in/tejas-borkar-03297b119","https://www.linkedin.com/in/anam-ansari-673bb7207/","https://www.linkedin.com/in/wilfred-almeida/")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        val adapter = context?.let { ProfileAdapter(names ,images,  it) }
        binding.recyclerViewProfiles.layoutManager = GridLayoutManager(this.context,2)
        binding.recyclerViewProfiles.adapter = adapter

        adapter?.setOnClick(this)
        return binding.root

    }
    override fun onItemClick(position: Int) {
        var url = linkedinID[position]
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "http://$url"
        }
        val openUrlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(openUrlIntent)
    }
}