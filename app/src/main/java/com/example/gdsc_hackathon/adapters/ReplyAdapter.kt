package com.example.gdsc_hackathon.adapters

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Prefs
import com.example.gdsc_hackathon.dataModel.Reply
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*

class ReplyAdapter(options: FirestoreRecyclerOptions<Reply>) : FirestoreRecyclerAdapter<Reply, ReplyAdapter.ReplyHolder>(options) {

    override fun onBindViewHolder(holder: ReplyHolder, position: Int, model: Reply) {
        holder.textViewReply.text = model.reply
        if(holder.date == model.date)
            holder.textViewDate.text = model.time
        else
            holder.textViewDate.text = model.date
        if(holder.user!!.uid == model.uid){
            holder.textViewUsername.text = "Me"
            holder.deleteReply.visibility = View.VISIBLE
        }
        else{
            holder.textViewUsername.text = model.username
        }
        holder.deleteReply.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setMessage("Are you sure you want to delete this reply?").setPositiveButton(
                "Yes"
            ) { dialogInterface: DialogInterface?, i: Int ->
                deleteItem(position)
            }
                .setNegativeButton(
                    "No"
                ) { dialogInterface: DialogInterface, i: Int -> dialogInterface.cancel() }
            val alertDialog = builder.create()
            alertDialog.show()
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
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        Firebase.firestore.collection("users").document(uid).get().addOnCompleteListener{ user ->
            val value : Int = user.result.getLong("questionsReplied")!!.toInt()
            if(value <= 0) {
                Firebase.firestore.collection("users").document(uid).update("questionsReplied", 0)
            }
            else {
                Firebase.firestore.collection("users").document(uid).update("questionsReplied", value - 1)
            }
        }
    }

    class ReplyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewReply: TextView = itemView.findViewById(R.id.text_view_reply)
        var textViewUsername: TextView = itemView.findViewById(R.id.text_view_username)
        var textViewDate: TextView = itemView.findViewById(R.id.text_view_current_date)
        var deleteReply : ImageButton = itemView.findViewById(R.id.fab_delete_reply)
        val user = FirebaseAuth.getInstance().currentUser
        private val prefs : Prefs = Prefs(itemView.context)
        val username = prefs.username
        private val dateFormat1 = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
        val date = dateFormat1.format(Date())
    }

}