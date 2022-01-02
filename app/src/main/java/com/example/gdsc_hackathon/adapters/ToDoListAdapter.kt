package com.example.gdsc_hackathon.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.databinding.SingleToDoItemBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.gdsc_hackathon.dataModel.ToDo

class ToDoListAdapter(private val onItemClicked: (ToDo) -> Unit) :
    ListAdapter<ToDo, ToDoListAdapter.ToDoViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            SingleToDoItemBinding.inflate(
                LayoutInflater.from(
                    parent.context,

                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val current = getToDo(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }
    fun getToDo(position: Int): ToDo{
        return getItem(position)
    }

    class ToDoViewHolder(private var binding: SingleToDoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: ToDo) {
            binding.todoListTitle.text = todo.title
            binding.todoListDescription.text = todo.description

        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ToDo>() {
            override fun areItemsTheSame(oldTodo: ToDo, newTodo: ToDo): Boolean {
                return oldTodo === newTodo
            }

            override fun areContentsTheSame(oldTodo: ToDo, newTodo: ToDo): Boolean {
                return oldTodo.title == newTodo.title
            }
        }
    }
}
