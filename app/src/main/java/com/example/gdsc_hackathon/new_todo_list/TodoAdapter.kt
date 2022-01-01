package com.example.gdsc_hackathon.new_todo_list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import java.util.ArrayList

class TodoAdapter(var todoList: List<Todo>? = ArrayList<Todo>()): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    private var onTodoItemClickedListener: OnTodoItemClickedListener?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layout = if (itemCount == 0) R.layout.empty_view else R.layout.todo_item_view
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return TodoViewHolder(view, todoList!!)
    }

    override fun getItemCount(): Int {
        return if(todoList!!.isEmpty()) 0 else todoList!!.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int){
        holder.view.setOnClickListener{onTodoItemClickedListener!!.onTodoItemClicked(todoList!!.get(position))}
        holder.view.setOnLongClickListener{
            onTodoItemClickedListener!!.onTodoItemLongClicked(todoList!!.get(position))
            true
        }
        holder.onBindViews(position)
    }

    inner class TodoViewHolder(val view: View, val todoList: List<Todo>): RecyclerView.ViewHolder(view){
        fun onBindViews(position: Int){
            if (itemCount != 0){
                view.findViewById<TextView>(R.id.title).text = todoList.get(position).title
                view.findViewById<TextView>(R.id.first_letter).text = todoList.get(position).title.first().toUpperCase().toString()
                view.findViewById<ImageView>(R.id.priority_imgView).setImageResource(getImage(todoList.get(position).priority))
            }

        }
        private fun getImage(priority: Int): Int
                = if (priority == 1) R.drawable.low_priority else if(priority == 2) R.drawable.medium_priority else R.drawable.high_priority
    }

    fun setTodoItemClickedListener(onTodoItemClickedListener: OnTodoItemClickedListener){
        this.onTodoItemClickedListener = onTodoItemClickedListener
    }

    interface OnTodoItemClickedListener{
        fun onTodoItemClicked(todo: Todo)
        fun onTodoItemLongClicked(todo: Todo)
    }
}