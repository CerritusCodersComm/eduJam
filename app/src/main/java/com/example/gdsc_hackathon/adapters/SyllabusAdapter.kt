package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Syllabus
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.button.MaterialButton

class SyllabusAdapter(options: FirestoreRecyclerOptions<Syllabus>) :
    FirestoreRecyclerAdapter<Syllabus, SyllabusAdapter.SyllabusHolder>(options) {
    private var listener: SyllabusAdapter.OnItemClickListener? = null

    override fun onBindViewHolder(holder: SyllabusHolder, position: Int, model: Syllabus) {
        holder.textViewSubject.text = model.subjectName
        if(model.subjectName.equals("Applied Mathematics 4"))
            holder.buttonTutorials.text = "Checkout Tutorials"
        holder.layout.setOnClickListener {
            if (holder.buttonModules.visibility == View.VISIBLE && holder.buttonTutorials.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    holder.layout,
                    AutoTransition()
                )
                holder.buttonModules.visibility = View.GONE
                holder.buttonTutorials.visibility = View.GONE
                holder.textViewSubject.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_down_24, //right
                    0
                )
            } else {

                TransitionManager.beginDelayedTransition(
                    holder.layout,
                    AutoTransition()
                )
                holder.buttonTutorials.visibility = View.VISIBLE
                holder.buttonModules.visibility = View.VISIBLE
                holder.textViewSubject.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_up_24, //right
                    0
                )

            }
        }
        holder.buttonModules.setOnClickListener {
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener!!.onModuleClick(snapshots.getSnapshot(position).id)
            }
        }
        holder.buttonTutorials.setOnClickListener {
            if (position != RecyclerView.NO_POSITION && listener != null) {
                listener!!.onTutorialClick(snapshots.getSnapshot(position).id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SyllabusHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.syllabus_item,
            parent, false
        )
        return SyllabusHolder(v)
    }

    class SyllabusHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewSubject: TextView = itemView.findViewById(R.id.text_view_subject)
        var layout : RelativeLayout = itemView.findViewById(R.id.syllabus_item_layout)
        var buttonModules : MaterialButton = itemView.findViewById(R.id.button_checkout_modules)
        var buttonTutorials : MaterialButton = itemView.findViewById(R.id.button_checkout_tutorials)
    }

    interface OnItemClickListener {
        fun onModuleClick(documentSnapshot: String)
        fun onTutorialClick(documentSnapshot: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }

}

