package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Prefs


class ProfileFragment : Fragment(R.layout.fragment_profile) {

//    lateinit var binding:FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    val rootView: View = inflater.inflate(R.layout.fragment_profile, container, false)
    val name : TextView = rootView.findViewById(R.id.profile_user_full_name)
    val email : TextView = rootView.findViewById(R.id.profile_user_email)
    val department : TextView = rootView.findViewById(R.id.profile_department)
    val prefs= Prefs(rootView.context)
    name.text = prefs.name
    email.text = prefs.email
    department.text = prefs.department
    return rootView
    }

}