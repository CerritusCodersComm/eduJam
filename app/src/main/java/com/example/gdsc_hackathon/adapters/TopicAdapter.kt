package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Topic
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class TopicAdapter(options: FirestoreRecyclerOptions<Topic>) :
    FirestoreRecyclerAdapter<Topic, TopicAdapter.TopicHolder>(options) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.topic_item,
            parent, false
        )
        return TopicHolder(v)
    }

    override fun onBindViewHolder(holder: TopicHolder, position: Int, model: Topic) {
        holder.textViewTopic.text = model.topicName
    }

    class TopicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTopic : TextView = itemView.findViewById(R.id.text_view_topic)
        var textViewHours : TextView = itemView.findViewById(R.id.text_view_hours)
        var textViewDiscussion : TextView = itemView.findViewById(R.id.text_view_discussion)
    }
}