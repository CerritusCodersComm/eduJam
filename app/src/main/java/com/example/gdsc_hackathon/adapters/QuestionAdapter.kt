package com.example.gdsc_hackathon.adapters

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.activities.MainActivity
import com.example.gdsc_hackathon.dataModel.Prefs
import com.example.gdsc_hackathon.dataModel.Question
import com.example.gdsc_hackathon.dataModel.User
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuestionAdapter(options: FirestoreRecyclerOptions<Question>) : FirestoreRecyclerAdapter<Question, QuestionAdapter.QuestionHolder>(options) {
    private var listener: OnItemClickListener? = null



    override fun onBindViewHolder(holder: QuestionHolder, position: Int, model: Question) {
        holder.textViewQuestion.text = model.question
        holder.textViewDate.text = model.date
        if(holder.username == model.username){
            holder.textViewUser.text = "Me"
            holder.deleteQuestion.visibility = View.VISIBLE
        }
        else{
            holder.textViewUser.text = model.username
        }
        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener!!.onItemClick(snapshots.getSnapshot(position).id)
            }
        }
        holder.deleteQuestion.setOnClickListener(View.OnClickListener {
            deleteItem(position)
        })


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : QuestionHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionHolder(v)
    }

    open fun deleteItem(position: Int) {
        snapshots.getSnapshot(position).reference.delete()
    }


    class QuestionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewQuestion: TextView = itemView.findViewById(R.id.text_view_question)
        var textViewUser: TextView = itemView.findViewById(R.id.text_view_user)
        var textViewDate: TextView = itemView.findViewById(R.id.text_view_date)
        var deleteQuestion : FloatingActionButton = itemView.findViewById(R.id.fab_delete_question)
        private val user = FirebaseAuth.getInstance().currentUser
        val prefs : Prefs = Prefs(itemView.context)
        val username = prefs.username

        init {

        }
    }

    interface OnItemClickListener {
        fun onItemClick(documentSnapshot: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}