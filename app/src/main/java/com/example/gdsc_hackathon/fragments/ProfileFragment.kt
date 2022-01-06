package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.extensions.timer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var  name:TextView
    private lateinit var  email:TextView
    private lateinit var  department:TextView
    private lateinit var  username:TextView
    private lateinit var  questionsAsked:TextView
    private lateinit var  questionsReplied:TextView
    private lateinit var  profileSemester:TextView
    private lateinit var  progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_profile, container, false)
        name = rootView.findViewById(R.id.profile_user_full_name)
        email = rootView.findViewById(R.id.profile_user_email)
        department = rootView.findViewById(R.id.profile_department)
        username = rootView.findViewById(R.id.profile_username)
        questionsAsked = rootView.findViewById(R.id.profile_questions_asked_number)
        questionsReplied = rootView.findViewById(R.id.profile_questions_replied_number)
        profileSemester = rootView.findViewById(R.id.profile_semester)
        progressBar = rootView.findViewById(R.id.progress_bar)

        val user = FirebaseAuth.getInstance().currentUser
        timer(progressBar, 400)
        Firebase.firestore.collection("users").document(user!!.uid).get().addOnCompleteListener {
            val doc = it.result
            if(doc!=null) {
                username.text= doc.getString("username").toString()
                name.text = doc.getString("name").toString()
                email.text = doc.getString("email").toString()
                department.text=  doc.getString("department").toString()
                    profileSemester.text = doc.getString("semester").toString()
                questionsAsked.text =  doc.getLong("questionsAsked").toString()
                    questionsReplied.text =  doc.getLong("questionsReplied").toString()
            }
        }
        return rootView
    }
}