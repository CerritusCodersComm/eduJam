package com.example.gdsc_hackathon.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.activities.AddToDoItem
import com.example.gdsc_hackathon.adapters.ToDoListAdapter
import com.example.gdsc_hackathon.dataModel.ToDoModel
import com.example.gdsc_hackathon.databinding.FragmentTodoListBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader


class TodoListFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentTodoListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        binding.floatingActionButtonToAddItemList.setOnClickListener(this)
        getTodoListData()



        return binding.root

    }

    private fun getTodoListData() {
        binding.recycleViewForTodoList.layoutManager = LinearLayoutManager(activity)
        val path = activity?.filesDir?.absolutePath?.toString()
        if (path == null) {
            Toast.makeText(activity, "Files are not getting fetch", Toast.LENGTH_LONG).show()
        }
        else {
            val toDoList = mutableListOf<ToDoModel>()
            var fileInputStream: FileInputStream?
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String?
            Log.d("Files", "Path: $path")
            val directory = File(path)
            Log.d("Files", "Path: $path")
            val files = directory.listFiles()
            Log.d("Files", "Size: " + files?.size)
            if (files != null)
                for (i in files.indices) {
                    fileInputStream = context?.openFileInput(files[i].name)
                    val inputStreamReader = InputStreamReader(fileInputStream)
                    val bufferedReader = BufferedReader(inputStreamReader)
                    while (run {
                            text = bufferedReader.readLine()
                            text
                        } != null) {
                        stringBuilder.append(text)
                    }
                    val todo = ToDoModel(files[i].name, stringBuilder.toString())
                    toDoList.add(todo)
                    Log.d("Files", "FileName:" + files[i].name)
                }
            val adapter = activity?.let { ToDoListAdapter(it, toDoList) }
            binding.recycleViewForTodoList.adapter = adapter

        }

    }

    override fun onClick(p0: View?) {

        when (p0?.id) {
            R.id.floating_action_button_to_add_item_list -> {

                activity?.let {
                    val intent = Intent(it, AddToDoItem::class.java)
                    it.startActivity(intent)
                }

            }
        }


    }

    override fun onResume() {
        super.onResume()
        getTodoListData()
    }

}