package com.example.gdsc_hackathon.adapters


import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.activities.AddToDoItem
import com.example.gdsc_hackathon.dataModel.ToDoModel
import com.example.gdsc_hackathon.databinding.SingleToDoItemBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File


class ToDoListAdapter(
    private val activity: Activity,

    ) : RecyclerView.Adapter<ToDoListAdapter.ViewHolder>() {
    class ViewHolder(view: SingleToDoItemBinding) : RecyclerView.ViewHolder(view.root) {
        val titleText = view.todoListTitle
        val description = view.todoListDescription
        val todoItem = view.todoListSingleItem

    }

    private var listItems: ArrayList<ToDoModel> = getToDoList()
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
        holder.todoItem.setOnClickListener {
            val intent = Intent(activity, AddToDoItem::class.java)
            intent.putExtra(AddToDoItem.TITLE, item.title)
            intent.putExtra(AddToDoItem.DESCRIPTION, item.description)
            activity.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun removeItem(position: Int, view: View) {
        val deletedTodo: ToDoModel = listItems[position]
        val directory =
            File("${activity.filesDir?.absolutePath?.toString()}/${AddToDoItem.FILE_DIRECTORY}")
        File(directory, "${deletedTodo.title}.txt").delete()
        swapItems(getToDoList(), position, true)
        val snackBar: Snackbar =
            Snackbar.make(view, "${deletedTodo.title} removed", Snackbar.LENGTH_LONG)
        snackBar.setAction("UNDO") {
            val file = File(directory, "${deletedTodo.title}.txt")
            file.appendText(deletedTodo.description)
            swapItems(getToDoList(), position, false)
        }
        snackBar.anchorView =
            activity.findViewById(com.example.gdsc_hackathon.R.id.bottom_navigation)
        snackBar.show()


    }

    private fun swapItems(listItems: ArrayList<ToDoModel>, position: Int, delete: Boolean) {
        this.listItems = listItems
        if (delete)
            notifyItemRemoved(position)
        else
            notifyItemInserted(position)
    }

    private fun getToDoList(): ArrayList<ToDoModel> {
        val toDoList = ArrayList<ToDoModel>()
        val path = activity.filesDir?.absolutePath?.toString()
        if (path == null) {
            Toast.makeText(activity, "Files are not getting fetch", Toast.LENGTH_LONG).show()

        } else {

            val directory = File("$path/${AddToDoItem.FILE_DIRECTORY}")
            if (!directory.exists()) {
                directory.mkdir()
            }
            val files = directory.listFiles()
            if (files != null)
                for (file in files) {
                    val title = file.name.split(".txt")[0]
                    val description = file.readText()

                    val todo = ToDoModel(title, description)
                    toDoList.add(todo)
                }

        }
        return toDoList
    }


}
