package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Practical
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class PracticalsAdapter(options: FirestoreRecyclerOptions<Practical>) :
    FirestoreRecyclerAdapter<Practical, PracticalsAdapter.PracticalHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PracticalHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.topic_item,
            parent, false
        )
        return PracticalHolder(v)
    }

    override fun onBindViewHolder(holder: PracticalHolder, position: Int, model: Practical) {
        holder.textViewTopic.text = model.practicalName
    }

    class PracticalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTopic : TextView = itemView.findViewById(R.id.text_view_topic)
        var textViewHours : TextView = itemView.findViewById(R.id.text_view_hours)
        var textViewDiscussion : TextView = itemView.findViewById(R.id.text_view_discussion)
    }
}
