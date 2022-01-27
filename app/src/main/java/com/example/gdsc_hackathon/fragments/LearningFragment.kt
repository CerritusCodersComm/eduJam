package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.LearningAdapter
import com.example.gdsc_hackathon.dataModel.Learning
import com.example.gdsc_hackathon.extensions.closeKeyboard
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class LearningFragment: Fragment(R.layout.fragment_learning) {
    private lateinit var recyclerViewLearning: RecyclerView
    private lateinit var adapter: LearningAdapter
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val ref: CollectionReference = db.collection("Learning")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_learning, container, false)
        recyclerViewLearning = rootView.findViewById(R.id.recycler_view_learning)
        val query = ref.orderBy(FieldPath.documentId(), Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Learning>().setQuery(query, Learning::class.java).build()
        adapter = LearningAdapter(options)
        recyclerViewLearning.layoutManager = LinearLayoutManager(context)
        recyclerViewLearning.adapter = adapter
        adapter.setOnItemClickListener(object : LearningAdapter.OnItemClickListener {
            override fun onItemClick(documentSnapshot: String) {
                val bundle = bundleOf("id" to documentSnapshot)
                closeKeyboard()
                rootView.findNavController().navigate(R.id.action_learningFragment_to_subTopicFragment, bundle)
            }
        })
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