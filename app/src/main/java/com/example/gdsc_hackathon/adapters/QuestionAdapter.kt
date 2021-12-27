package com.example.gdsc_hackathon.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Question
import com.example.gdsc_hackathon.dataModel.Reply
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class QuestionAdapter(options: FirestoreRecyclerOptions<Question>) : FirestoreRecyclerAdapter<Question, QuestionAdapter.QuestionHolder>(options) {
    private val db = FirebaseFirestore.getInstance()
    var quesRef = db.collection("Questions")
    var listener: OnItemClickListener? = null

    private val viewPool = RecycledViewPool()

    override fun onBindViewHolder(holder: QuestionHolder, position: Int, model: Question) {
        holder.textViewQuestion.text = model.getQuestionText()
        holder.textViewUser.text = model.getUser()
        holder.textViewDate.text = model.getDate()

        val id = snapshots.getSnapshot(position).id
        Log.d("myTag", id)
        val queryReplies =
            quesRef.document(id).collection("Replies").orderBy("date", Query.Direction.ASCENDING)
        val optionsReplies: FirestoreRecyclerOptions<Reply> =
            FirestoreRecyclerOptions.Builder<Reply>()
                .setQuery(queryReplies, Reply::class.java)
                .build()
        holder.replyAdapter = ReplyAdapter(optionsReplies)
        holder.recyclerViewReply.setHasFixedSize(true)
        holder.recyclerViewReply.layoutManager = LinearLayoutManager(holder.recyclerViewReply.context)
        holder.recyclerViewReply.adapter = holder.replyAdapter
        holder.recyclerViewReply.setRecycledViewPool(viewPool)
        holder.replyAdapter!!.startListening()
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
                holder.replyAdapter!!.deleteItem(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(holder.recyclerViewReply)

        holder.itemView.setOnClickListener {
            if (holder.hiddenReplies!!.visibility == View.VISIBLE) {
                holder.hiddenReplies?.visibility = View.GONE
            } else {
                if (listener != null) {
                    holder.hiddenReplies?.visibility = View.VISIBLE
                    holder.buttonReply.setOnClickListener(View.OnClickListener {
                        val reply: String = holder.editTextReply.getText().toString()
                        listener!!.onButtonClick(snapshots.getSnapshot(position).id, position, reply)
                        holder.editTextReply.setText("")
                        holder.replyAdapter!!.startListening()
                    })
                }
            }
        }
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
        var recyclerViewReply : RecyclerView = itemView.findViewById(R.id.recycler_view_replies)
        var hiddenReplies : View? = itemView.findViewById(R.id.hidden_replies)
        var editTextReply : EditText = itemView.findViewById(R.id.edit_text_reply)
        var buttonReply : Button = itemView.findViewById(R.id.button_reply)
        var replyAdapter: ReplyAdapter? = null

        init {
        }
    }

    interface OnItemClickListener {
        fun onButtonClick(documentSnapshot: String, position: Int, reply: String?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}