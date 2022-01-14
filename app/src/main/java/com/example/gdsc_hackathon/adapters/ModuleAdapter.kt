package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.example.gdsc_hackathon.dataModel.Module
import com.example.gdsc_hackathon.dataModel.Topic
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ModuleAdapter(options: FirestoreRecyclerOptions<Module>) :
    FirestoreRecyclerAdapter<Module, ModuleAdapter.ModuleHolder>(options) {
    val ref = FirebaseFirestore.getInstance().collection("Syllabus")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_module_layout,
            parent, false
        )
        return ModuleHolder(v)
    }

    override fun onBindViewHolder(holder: ModuleHolder, position: Int, model: Module) {
        holder.textViewTopic.text = model.moduleName
        holder.module = "module$position"
        val query = ref.document("SEM4").collection("COMP").document("M4").collection("Modules").document(snapshots.getSnapshot(position).id).collection("topics").orderBy(
            FieldPath.documentId(), Query.Direction.ASCENDING)
        val options = FirestoreRecyclerOptions.Builder<Topic>().setQuery(query, Topic::class.java).build()
        holder.adapter = TopicAdapter(options)
        holder.recyclerViewTopic.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.recyclerViewTopic.adapter = holder.adapter
        holder.adapter.startListening()
    }

    class ModuleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTopic : TextView = itemView.findViewById(R.id.text_view_module)
        var recyclerViewTopic : RecyclerView = itemView.findViewById(R.id.recycler_view_topic)
        lateinit var adapter : TopicAdapter
        lateinit var module : String
    }

}