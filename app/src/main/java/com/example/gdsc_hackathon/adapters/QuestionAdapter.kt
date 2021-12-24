package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Question
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class QuestionAdapter(options: FirestoreRecyclerOptions<Question>) : FirestoreRecyclerAdapter<Question, QuestionAdapter.QuestionHolder>(options) {

    override fun onBindViewHolder(holder: QuestionHolder, position: Int, model: Question) {
        holder.textViewQuestion.text = model.getQuestionText()
        holder.textViewUser.text = model.getUser()
        holder.textViewDate.text = model.getDate()
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
    }
}