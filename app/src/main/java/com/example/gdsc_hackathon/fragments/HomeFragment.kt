package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.navigation.findNavController
import com.example.gdsc_hackathon.R
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.awaitAll

class HomeFragment : Fragment() {
    lateinit var syllabusLayout: LinearLayout
    lateinit var weeklyTimeTableLayout: LinearLayout
    lateinit var holidayLayout: LinearLayout
    lateinit var examTimeConstraintLayout: LinearLayout
    lateinit var practicalLayout: LinearLayout
    lateinit var previousYearPapersLayout: LinearLayout
    lateinit var academicCalendarLayout: LinearLayout
    lateinit var moreLayout: LinearLayout

    private lateinit var mAuth : FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        Log.w("LOOK",currentUser?.uid+"")
        Log.w("LOOK",currentUser?.displayName+"")
        Log.w("LOOK",currentUser?.email+"")

        val usr = hashMapOf(
            "uid" to currentUser?.uid,
            "name" to currentUser?.displayName,
            "email" to currentUser?.email,
        );

        Firebase.firestore.collection("users").document(currentUser?.uid!!).set(usr)
//            .addOnSuccessListener { documentReference ->
//                Log.d("LOOK", "DocumentSnapshot added") }
//            .addOnFailureListener { e ->
//                Log.w("LOOK", "Error adding document", e)
//            }


        val rootView: View = inflater.inflate(R.layout.fragment_home, container, false)

        syllabusLayout = rootView.findViewById(R.id.syllabusLayout)
        syllabusLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.syllabusFragment)
        }

        weeklyTimeTableLayout = rootView.findViewById(R.id.weeklyTimeTableLayout)
        weeklyTimeTableLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.weeklyTimeTableFragment)
        }

        holidayLayout = rootView.findViewById(R.id.holidayLayout)
        holidayLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.holidayFragment)
        }

        examTimeConstraintLayout = rootView.findViewById(R.id.examTimeConstraintLayout)
        examTimeConstraintLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.examTimeConstraintFragment)
        }

        practicalLayout = rootView.findViewById(R.id.practicalLayout)
        practicalLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.practicalFragment)
        }

        previousYearPapersLayout = rootView.findViewById(R.id.previousYearPapersLayout)
        previousYearPapersLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.previousYearPapersFragment)
        }

        academicCalendarLayout = rootView.findViewById(R.id.academicCalendarLayout)
        academicCalendarLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.academicCalendarFragment)
        }

        moreLayout = rootView.findViewById(R.id.moreLayout)
        moreLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.moreFragment)
        }

        return rootView
    }
}

