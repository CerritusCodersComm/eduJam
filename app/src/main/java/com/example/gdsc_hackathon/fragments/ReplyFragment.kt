package com.example.gdsc_hackathon.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.ReplyAdapter
import com.example.gdsc_hackathon.dataModel.Reply
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.*


class ReplyFragment : Fragment (R.layout.fragment_reply) {
    private val db = FirebaseFirestore.getInstance()
    var quesRef = db.collection("Questions")
    lateinit var replyAdapter : ReplyAdapter
    lateinit var recyclerViewReply : RecyclerView
    lateinit var editTextReply : EditText
    lateinit var textViewQuestion : TextView
    lateinit var textViewDate : TextView
    lateinit var textViewUser : TextView
    lateinit var buttonReply : Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView: View = inflater.inflate(R.layout.fragment_reply, container, false)
        recyclerViewReply = rootView.findViewById(R.id.recycler_view_replies)
        editTextReply = rootView.findViewById(R.id.edit_text_reply)
        textViewQuestion = rootView.findViewById(R.id.text_view_question)
        textViewUser = rootView.findViewById(R.id.text_view_user_reply)
        textViewDate = rootView.findViewById(R.id.text_view_date_reply)
        buttonReply= rootView.findViewById(R.id.button_reply)

        val bundle = arguments
        val id = bundle!!.getString("id")
        if (id != null) {
            quesRef.document(id).get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val question = documentSnapshot.getString("question")
                        val date = documentSnapshot.getString("currentDate")
                        val username = documentSnapshot.getString("username")
                        textViewQuestion.setText(question)
                        textViewDate.setText(date)
                        textViewUser.setText(username)
                    } else {
                        Toast.makeText(context, "Document does not exist", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                .addOnFailureListener(OnFailureListener { e ->
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, e.toString())
                })
            setUpQuestion(id)
            setUpRecyclerView(id)
        }

        buttonReply.setOnClickListener(View.OnClickListener {
            var reply : String = ""
            if(editTextReply.text != null) {
                 reply = editTextReply.text.toString()
            }
            if (id != null) {
                replyToQuestion(id, reply)
            }
        })
        return rootView
        }



    private fun setUpQuestion(id: String) {

    }

    private fun setUpRecyclerView(id : String) {
        val queryReplies = quesRef.document(id).collection("Replies").orderBy("date", Query.Direction.ASCENDING)
        val optionsReplies: FirestoreRecyclerOptions<Reply> = FirestoreRecyclerOptions.Builder<Reply>().setQuery(queryReplies, Reply::class.java).build()
        replyAdapter = ReplyAdapter(optionsReplies)
        recyclerViewReply.setHasFixedSize(true)
        recyclerViewReply.layoutManager = LinearLayoutManager(recyclerViewReply.context)
        recyclerViewReply.adapter = replyAdapter

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
                replyAdapter.deleteItem(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(recyclerViewReply)

    }

    fun replyToQuestion(id : String, reply : String){
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH.mm.ss")
        val currentDate = dateFormat.format(Date())
        val replyModel = Reply(id, reply, "anam", currentDate)
        quesRef.document(id).collection("Replies").add(replyModel).addOnSuccessListener {
            editTextReply.text = null
        }.addOnFailureListener(OnFailureListener {
            Toast.makeText(activity, "Failed", Toast.LENGTH_LONG).show()
        })
    }

    override fun onStart() {
        super.onStart()
        replyAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        replyAdapter.stopListening()
    }

}