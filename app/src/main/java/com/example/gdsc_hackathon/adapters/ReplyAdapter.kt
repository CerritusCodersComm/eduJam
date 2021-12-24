package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Reply
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import java.lang.String

class ReplyAdapter(options: FirestoreRecyclerOptions<Reply>) : FirestoreRecyclerAdapter<Reply, ReplyAdapter.ReplyHolder>(options) {

    override fun onBindViewHolder(holder: ReplyHolder, position: Int, model: Reply) {
        holder.textViewReply.setText(model.getReplyText())
        holder.username.setText(model.getUser())
        holder.date.setText(String.valueOf(model.getCurrentDate()))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.reply_item,
            parent, false
        )
        return ReplyHolder(v)
    }

    fun deleteItem(position: Int) {
        snapshots.getSnapshot(position).reference.delete()
    }

    class ReplyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewReply: TextView = itemView.findViewById(R.id.text_view_reply)
        var username: TextView = itemView.findViewById(R.id.text_view_username)
        var date: TextView = itemView.findViewById(R.id.text_view_current_date)

    }

}