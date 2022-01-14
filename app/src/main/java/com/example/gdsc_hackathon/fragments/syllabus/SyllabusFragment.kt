package com.example.gdsc_hackathon.fragments.syllabus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.HolidaysItemAdapter
import com.example.gdsc_hackathon.adapters.QuestionAdapter
import com.example.gdsc_hackathon.adapters.SyllabusAdapter
import com.example.gdsc_hackathon.dataModel.HolidaysItemModel
import com.example.gdsc_hackathon.dataModel.Question
import com.example.gdsc_hackathon.dataModel.Syllabus
import com.example.gdsc_hackathon.extensions.closeKeyboard
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.util.ArrayList

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
                rootView.findNavController().navigate(R.id.moduleFragment)
            }

            override fun onTutorialClick(documentSnapshot: String) {
                rootView.findNavController().navigate(R.id.mathsTutorialFragment)
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

