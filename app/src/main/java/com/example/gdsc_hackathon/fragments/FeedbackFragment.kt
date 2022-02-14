package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.FeedbackModel
import com.example.gdsc_hackathon.databinding.FragmentFeedbackBinding
import com.example.gdsc_hackathon.extensions.closeKeyboard
import com.example.gdsc_hackathon.extensions.showSnackBar
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
            submitFeedbackForm(binding.root)
            closeKeyboard()
        }
        return binding.root
    }

    private fun submitFeedbackForm(view: View) {
        val title: String = binding.titleOfFeedbackForm.text.toString()
        val description: String = binding.descriptionInFeedbackForm.text.toString()
        val email: String? = FirebaseAuth.getInstance().currentUser?.email

        if(title.isNotEmpty() && description.isNotEmpty()) {
            email?.let {
                val feedbackModel = FeedbackModel(title, description, it)
                feedbackRef.add(feedbackModel)
                    .addOnSuccessListener {
                        showSnackBar(requireActivity(), "Thanks you for giving us a feedback")
                        view.findNavController().navigate(R.id.homeFragment)
                    }
                    .addOnFailureListener { exception ->
                        showSnackBar(requireActivity(), "some unexpected error occurred")
                        Log.w("FeedbackFragment", "Error adding document $exception")
                    }
            }
        }
        else{
            showSnackBar(requireActivity(), "Please add proper feedback!")
        }
    }
}
