package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gdsc_hackathon.databinding.FragmentFeedbackBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FeedbackFragment : Fragment() {

    private var _binding: FragmentFeedbackBinding? = null
    private val binding get() = _binding!!
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
        val uid : String? = FirebaseAuth.getInstance().currentUser?.uid
        uid?.let {
            Firebase.firestore.collection("users").document(it).get().addOnCompleteListener{ user ->
                val value : Int = user.result.getLong("questionsAsked")!!.toInt()
                Firebase.firestore.collection("users").document(it).update("questionsAsked", value + 1)
            }
        }

    }


}






