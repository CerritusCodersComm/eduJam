package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdsc_hackathon.R


/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

//    lateinit var binding:FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    val rootView: View = inflater.inflate(R.layout.fragment_profile, container, false)

    return rootView
    }

}