package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.ModuleAdapter
import com.example.gdsc_hackathon.dataModel.Module
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ModuleFragment : Fragment() {
    private lateinit var viewPager : ViewPager2
    private lateinit var adapter : ModuleAdapter
    private lateinit var tabLayout : TabLayout
    private var reference = FirebaseFirestore.getInstance().collection("Syllabus").document("SEM4").collection("COMP")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_module, container, false)
        viewPager = rootView.findViewById(R.id.tabViewpager)

        val bundle = arguments
        val subjectName = bundle?.getString("subjectName").toString()

        val query = reference.document(subjectName).collection("Modules").orderBy(FieldPath.documentId(), Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Module>().setQuery(query, Module::class.java).build()
        adapter = ModuleAdapter(options,subjectName)
        viewPager.adapter = adapter
        return rootView
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}