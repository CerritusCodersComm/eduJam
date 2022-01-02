package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.app_database.ToDoApplication
import com.example.gdsc_hackathon.dataModel.ToDo
import com.example.gdsc_hackathon.databinding.FragmentToDoDetailBinding
import com.example.gdsc_hackathon.viewmodels.ToDoListViewModel
import com.example.gdsc_hackathon.viewmodels.ToDoListViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ToDoDetailFragment : Fragment() {
    private val navigationArgs: ToDoDetailFragmentArgs by navArgs()
    lateinit var todo: ToDo

    private val viewModel: ToDoListViewModel by activityViewModels {
        ToDoListViewModelFactory(
            (activity?.application as ToDoApplication).database.todoDao()
        )
    }

    private var _binding: FragmentToDoDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentToDoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun bind(todo: ToDo) {
        binding.apply {
            todoTitle.text = todo.title
            todoDescription.text = todo.description
            deleteTodo.setOnClickListener { showConfirmationDialog() }
            editTodo.setOnClickListener { editTodo() }
        }
    }


    private fun editTodo() {
        val action = ToDoDetailFragmentDirections.actionToDoDetailFragmentToAddToDOFragment(
            getString(R.string.edit_todo),
            todo.id
        )
        this.findNavController().navigate(action)
    }


    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                deleteTodo()
            }
            .show()
    }


    private fun deleteTodo() {
        viewModel.deleteToDo(todo)
        findNavController().navigateUp()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.toDoId

        viewModel.retrieveToDo(id).observe(this.viewLifecycleOwner) { selectedTodo ->
            todo = selectedTodo
            bind(todo)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

