package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.SubTopic
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class SubTopicAdapter(options: FirestoreRecyclerOptions<SubTopic>) :
    FirestoreRecyclerAdapter<SubTopic, SubTopicAdapter.SubTopicHolder>(options) {
    private var listener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTopicHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.learning_item,
            parent, false
        )
        return SubTopicHolder(v)
    }

    override fun onBindViewHolder(holder: SubTopicHolder, position: Int, model: SubTopic) {
        holder.textViewLearning.text = model.topicName
        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener!!.onItemClick(snapshots.getSnapshot(position).id)
            }
        }
    }

    class SubTopicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewLearning : TextView = itemView.findViewById(R.id.text_view_learning)
    }

    interface OnItemClickListener {
        fun onItemClick(documentSnapshot: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}