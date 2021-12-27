package com.example.gdsc_hackathon.adapters


import android.app.Activity
import android.content.Intent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.activities.AddToDoItem
import com.example.gdsc_hackathon.dataModel.ToDoModel
import com.example.gdsc_hackathon.databinding.SingleToDoItemBinding


class ToDoListAdapter(
    private val activity: Activity,
    private var listItems : ArrayList<ToDoModel>,
    ) : RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {
    class ViewHolder(view: SingleToDoItemBinding) : RecyclerView.ViewHolder(view.root) {
        val titleText = view.todoListTitle
        val description  = view.todoListDescription
        val todoItem = view.todoListSingleItem

    }
//    private var removedPosition: Int = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: SingleToDoItemBinding = SingleToDoItemBinding
            .inflate(LayoutInflater.from(activity), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.titleText.text = item.title
        holder.description.text = item.description
        holder.todoItem.setOnClickListener{
            val intent = Intent(activity, AddToDoItem::class.java)
            intent.putExtra(AddToDoItem.TITLE,item.title)
            intent.putExtra(AddToDoItem.DESCRIPTION,item.description)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
//    fun removeItem(position: Int, viewHolder: RecyclerView.ViewHolder) {
//        val deletedTodo :ToDoModel= listItems[position]
//        removedPosition = position
//        val directory = File("${activity.filesDir?.absolutePath?.toString()}/${AddToDoItem.FILE_DIRECTORY}")
//        File(directory, "${deletedTodo.title}.txt").delete()
//        listItems =  getToDoList()
//        notifyItemRemoved(position)
//        notifyItemRangeChanged(position, listItems.size)
//        Snackbar.make(viewHolder.itemView, "${deletedTodo.title} removed", Snackbar.LENGTH_LONG).setAction("UNDO") {
//            listItems.add(removedPosition, deletedTodo)
//            val file = File(directory, "${deletedTodo.title}.txt")
//            file.appendText(deletedTodo.description)
//            notifyItemInserted(removedPosition)
//            notifyItemRangeChanged(position, listItems.size)
//
//        }.show()
//    }


}
