package com.example.gdsc_hackathon.adapters


import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.gdsc_hackathon.dataModel.ToDoModel
import com.example.gdsc_hackathon.databinding.SingleToDoItemBinding


class ToDoListAdapter(
 private val activity: Activity,
    private val listItems: List<ToDoModel>,

) : RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {
    class ViewHolder(view: SingleToDoItemBinding) : RecyclerView.ViewHolder(view.root) {
        val titleText = view.todoListTitle
        val description  = view.todoListDescription

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ToDoListAdapter.ViewHolder {
        val binding: SingleToDoItemBinding = SingleToDoItemBinding
            .inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ToDoListAdapter.ViewHolder, position: Int) {
        val item = listItems[position]
        holder.titleText.text = item.title
        holder.description.text = item.description
//        holder.tvText.setOnClickListener{
//            if(activity is AddDish){
//                activity.selectedListItem(item,selection)
//            }
//        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}
