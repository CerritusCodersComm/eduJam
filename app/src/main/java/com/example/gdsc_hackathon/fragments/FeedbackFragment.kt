package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gdsc_hackathon.dataModel.FeedbackModel
import com.example.gdsc_hackathon.databinding.FragmentFeedbackBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FeedbackFragment : Fragment() {

    private var _binding: FragmentFeedbackBinding? = null
    private val binding get() = _binding!!
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val feedbackRef: CollectionReference = db.collection("feedback")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedbackBinding.inflate(inflater, container, false)
        binding.submitFeedBack.setOnClickListener {
            submitFeedbackForm()

        }

        return binding.root
    }

    private fun submitFeedbackForm() {
        val title: String = binding.titleOfFeedbackForm.text.toString()
        val description: String = binding.descriptionInFeedbackForm.text.toString()
        val email : String? = FirebaseAuth.getInstance().currentUser?.email

        email?.let {
            val feedbackModel: FeedbackModel = FeedbackModel(title,description,it)
          feedbackRef.add(feedbackModel)
              .addOnSuccessListener {
                  Toast.makeText(context,"Thanks you for giving us a feedback",Toast.LENGTH_LONG).show()
                  activity?.supportFragmentManager?.popBackStack()


              }
              .addOnFailureListener { exception ->

                  Toast.makeText(context,"some unexpected error occurred",Toast.LENGTH_LONG).show()
                  Log.w("FeedbackFragment", "Error adding document $exception")
              }
        }

    }


}






