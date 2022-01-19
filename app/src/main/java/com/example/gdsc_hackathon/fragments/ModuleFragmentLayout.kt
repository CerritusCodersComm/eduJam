package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.PracticalsAdapter
import com.example.gdsc_hackathon.dataModel.Practical
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ModuleFragmentLayout: Fragment() {
    private lateinit var adapter: PracticalsAdapter
    private lateinit var recyclerViewTopic : RecyclerView
    private lateinit var textViewTitle : TextView
    private var reference = FirebaseFirestore.getInstance().collection("Syllabus").document("SEM4").collection("COMP")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_module_layout, container, false)
        recyclerViewTopic = rootView.findViewById(R.id.recycler_view_topic)
        textViewTitle = rootView.findViewById(R.id.text_view_module)
        val bundle = arguments
        val subjectName = bundle?.getString("subjectName").toString()
        reference.document(subjectName).get().addOnCompleteListener {
            textViewTitle.text = it.result.getString("subjectName")
        }
        val query = reference.document(subjectName).collection("Practicals").orderBy(FieldPath.documentId(), Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Practical>().setQuery(query, Practical::class.java).build()
        adapter = PracticalsAdapter(options)
        recyclerViewTopic.layoutManager = LinearLayoutManager(context)
        recyclerViewTopic.adapter = adapter
        adapter.startListening()
        return rootView
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.startListening()
    }

}