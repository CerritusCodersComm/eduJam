package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Syllabus
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.button.MaterialButton

class SyllabusAdapter(options: FirestoreRecyclerOptions<Syllabus>) :
    FirestoreRecyclerAdapter<Syllabus, SyllabusAdapter.SyllabusHolder>(options) {

    override fun onBindViewHolder(holder: SyllabusHolder, position: Int, model: Syllabus) {
        holder.textViewSubject.text = model.subjectName
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
        //        var buttonModules : MaterialButton = itemView.findViewById(R.id.button_checkout_modules)
//        var buttonTutorials : MaterialButton = itemView.findViewById(R.id.button_checkout_tutorials)
    }

}

