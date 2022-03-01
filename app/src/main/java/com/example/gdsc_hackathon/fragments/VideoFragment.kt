package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.LearningAdapter
import com.example.gdsc_hackathon.adapters.VideoAdapter
import com.example.gdsc_hackathon.dataModel.Learning
import com.example.gdsc_hackathon.dataModel.Video
import com.example.gdsc_hackathon.databinding.FragmentVideoBinding
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class VideoFragment : Fragment() {
    private lateinit var binding: FragmentVideoBinding
    private lateinit var adapter: VideoAdapter
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val ref: CollectionReference = db.collection("Learning")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(layoutInflater)
        val view = binding.root
        val bundle = arguments
        val topicId = bundle!!.getString("topicId")
        val subTopicId = bundle.getString("subTopicId")
        if(topicId != null && subTopicId != null){
            val query = ref.document(topicId).collection("SubTopics").document(subTopicId).collection("Videos").orderBy(FieldPath.documentId(), Query.Direction.ASCENDING)
            val options = FirestoreRecyclerOptions.Builder<Video>().setQuery(query, Video::class.java).build()
            adapter = VideoAdapter(options)
            binding.recyclerViewVideo.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewVideo.adapter = adapter
        }


        return view
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