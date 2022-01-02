package com.example.gdsc_hackathon.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.ToDoListAdapter
import com.example.gdsc_hackathon.app_database.ToDoApplication
import com.example.gdsc_hackathon.dataModel.ToDo
import com.example.gdsc_hackathon.databinding.FragmentTodoListBinding
import com.example.gdsc_hackathon.viewmodels.ToDoListViewModel
import com.example.gdsc_hackathon.viewmodels.ToDoListViewModelFactory
import com.google.android.material.snackbar.Snackbar


class TodoListFragment : Fragment() {
    private val viewModel: ToDoListViewModel by activityViewModels {
        ToDoListViewModelFactory(
            (activity?.application as ToDoApplication).database.todoDao()
        )
    }

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ToDoListAdapter {
            val action =
                TodoListFragmentDirections.actionTodoListFragmentToToDoDetailFragment(it.id)
            this.findNavController().navigate(action)
        }

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
                view.let {
                    val todo: ToDo = adapter.getToDo(
                        viewHolder.absoluteAdapterPosition,
                    )
                    onDeleteToDo(todo)
                }

            }
        }).attachToRecyclerView(binding.recycleViewForTodoList)
//        binding.recycleViewForTodoList.setHasFixedSize(true)
        binding.recycleViewForTodoList.layoutManager = LinearLayoutManager(this.context)
        binding.recycleViewForTodoList.adapter = adapter
        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }

        binding.floatingActionButtonToAddItemList.setOnClickListener {
            val action = TodoListFragmentDirections.actionToDoListFragmentToAddToDoFragment(
                getString(R.string.add_todo)
            )
            this.findNavController().navigate(action)
        }
    }

    private fun onDeleteToDo(todo: ToDo) {
        viewModel.deleteToDo(todo)
        val snackBar: Snackbar? =
            view?.let { Snackbar.make(it, "${todo.title} removed", Snackbar.LENGTH_LONG) }
        snackBar?.setAction("UNDO") {
            viewModel.addNewToDo(
                todo.title,
                todo.description,
            )
        }
        if (snackBar != null) {
            snackBar.anchorView =
                activity?.findViewById(R.id.bottom_navigation)
            snackBar.show()
        }

    }

}