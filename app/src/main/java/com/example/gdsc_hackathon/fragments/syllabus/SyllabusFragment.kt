package com.example.gdsc_hackathon.fragments.syllabus

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
import com.example.gdsc_hackathon.adapters.SyllabusAdapter
import com.example.gdsc_hackathon.dataModel.Syllabus
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SyllabusFragment : Fragment(R.layout.fragment_forum) {
    private lateinit var adapter: SyllabusAdapter
    private lateinit var recyclerViewSyllabus: RecyclerView
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val ref: CollectionReference = db.collection("Syllabus")
    private val reference: CollectionReference = ref.document("SEM4").collection("COMP")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_syllabus, container, false)
        recyclerViewSyllabus = rootView.findViewById(R.id.recycler_view_syllabus)
        setUpRecyclerView(rootView)
        return rootView
    }

    private fun setUpRecyclerView(rootView: View) {
        val query = reference.orderBy("subjectName", Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Syllabus>().setQuery(query, Syllabus::class.java).build()
        adapter = SyllabusAdapter(options)
//        recyclerViewSyllabus.setHasFixedSize(true)
        recyclerViewSyllabus.layoutManager = LinearLayoutManager(context)
        recyclerViewSyllabus.adapter = adapter
        adapter.startListening()

        adapter.setOnItemClickListener(object : SyllabusAdapter.OnItemClickListener {
            override fun onModuleClick(documentSnapshot: String) {
                val bundle = bundleOf("subjectName" to documentSnapshot)
                rootView.findNavController().navigate(R.id.action_syllabus_fragment_to_module_fragment,bundle)
            }

            override fun onTutorialClick(documentSnapshot: String) {
                val bundle = bundleOf("subjectName" to documentSnapshot)
                rootView.findNavController().navigate(R.id.action_syllabus_fragment_to_module_fragment_layout, bundle)
            }
        })
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

