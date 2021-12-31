package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Prefs
import com.example.gdsc_hackathon.dataModel.Reply
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import java.lang.String

class ReplyAdapter(options: FirestoreRecyclerOptions<Reply>) : FirestoreRecyclerAdapter<Reply, ReplyAdapter.ReplyHolder>(options) {

    override fun onBindViewHolder(holder: ReplyHolder, position: Int, model: Reply) {
        holder.textViewReply.text = model.reply
        holder.date.text = String.valueOf(model.date)
        if(holder.user!!.uid == model.uid){
            holder.textViewUsername.text = "Me"
            holder.deleteReply.visibility = View.VISIBLE
        }
        else{
            holder.textViewUsername.text = model.username
        }
        holder.deleteReply.setOnClickListener {
            deleteItem(position)
        }

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
        var textViewUsername: TextView = itemView.findViewById(R.id.text_view_username)
        var date: TextView = itemView.findViewById(R.id.text_view_current_date)
        var deleteReply : FloatingActionButton = itemView.findViewById(R.id.fab_delete_reply)
        val user = FirebaseAuth.getInstance().currentUser
        val prefs : Prefs = Prefs(itemView.context)
        val username = prefs.username
    }

}