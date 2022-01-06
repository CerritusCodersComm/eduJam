package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Prefs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


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
    val username : TextView = rootView.findViewById(R.id.profile_username)
    val questionsAsked : TextView = rootView.findViewById(R.id.profile_questions_asked_number)
    val questionsReplied : TextView = rootView.findViewById(R.id.profile_questions_replied_number)
    val profileSemester : TextView = rootView.findViewById(R.id.profile_semester)
    val user = FirebaseAuth.getInstance().currentUser
    Firebase.firestore.collection("users").document(user!!.uid).get().addOnCompleteListener{
        val doc = it.result
        name.text = doc.getString("name").toString()
        email.text = doc.getString("email").toString()
        department.text = doc.getString("department").toString()
        username.text = doc.getString("username").toString()
        questionsAsked.text = doc.getLong("questionsAsked").toString()
        questionsReplied.text = doc.getLong("questionsReplied").toString()
        profileSemester.text = doc.getString("semester").toString()
    }
    return rootView
    }

}