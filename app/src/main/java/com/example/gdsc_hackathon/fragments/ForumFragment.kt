package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.QuestionAdapter
import com.example.gdsc_hackathon.dataModel.Question
import com.example.gdsc_hackathon.dataModel.Reply
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*


class ForumFragment : Fragment(R.layout.fragment_forum) {
    lateinit var editTextQuestion : EditText
    lateinit var buttonAsk: Button
    lateinit var recyclerView: RecyclerView
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val quesRef: CollectionReference = db.collection("Questions")
    lateinit var adapter : QuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_forum, container, false)
        editTextQuestion = rootView.findViewById(R.id.edit_text_question)
        buttonAsk = rootView.findViewById(R.id.button_ask)
        recyclerView= rootView.findViewById(R.id.recycler_view_questions)
        setUpRecyclerView(rootView)
        buttonAsk.setOnClickListener(View.OnClickListener { addQuestion() })

        return rootView
    }


    private fun setUpRecyclerView(rootView : View) {
        //Query to get questions ordered by date
        val query = quesRef.orderBy("date", Query.Direction.DESCENDING)
        val options = FirestoreRecyclerOptions.Builder<Question>().setQuery(query, Question::class.java).build()

        adapter = QuestionAdapter(options)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        //This will delete the question
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.deleteItem(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(recyclerView)

        adapter.setOnItemClickListener(object : QuestionAdapter.OnItemClickListener {
            override fun onItemClick(documentSnapshot: String) {
                val bundle = bundleOf("id" to documentSnapshot)
                rootView.findNavController().navigate(R.id.action_forumFragment_to_replyFragment, bundle)
            }

        })
    }


    //Adds new Question
    private fun addQuestion() {
        val question: String = editTextQuestion.text.toString()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH.mm.ss")
        val currentDate = dateFormat.format(Date())
        val questionModel = Question(question, "anam", currentDate)
        quesRef.add(questionModel).addOnSuccessListener(OnSuccessListener {
            editTextQuestion.text = null
        }).addOnFailureListener(OnFailureListener {
            Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show()
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