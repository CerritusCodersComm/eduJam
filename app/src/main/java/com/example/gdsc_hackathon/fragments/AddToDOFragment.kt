package com.example.gdsc_hackathon.fragments


import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gdsc_hackathon.database.ToDoApplication
import com.example.gdsc_hackathon.dataModel.ToDo
import com.example.gdsc_hackathon.databinding.FragmentAddToDoBinding
import com.example.gdsc_hackathon.viewmodels.ToDoListViewModel
import com.example.gdsc_hackathon.viewmodels.ToDoListViewModelFactory


class AddToDoFragment : Fragment() {


    private val viewModel: ToDoListViewModel by activityViewModels {
        ToDoListViewModelFactory(
            (activity?.application as ToDoApplication).database
                .todoDao()
        )
    }
    private val navigationArgs: ToDoDetailFragmentArgs by navArgs()

    lateinit var todo: ToDo

    private var _binding: FragmentAddToDoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddToDoBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.titleInAddTodo.text.toString(),
            binding.descriptionInAddTodo.text.toString(),

        )
    }


    private fun bind(todo: ToDo) {

        binding.apply {
            titleInAddTodo.setText(todo.title, TextView.BufferType.SPANNABLE)
            descriptionInAddTodo.setText(todo.description, TextView.BufferType.SPANNABLE)

            submitToDo.setOnClickListener { updateTodo() }
        }
    }


    private fun addNewToDo() {
        if (isEntryValid()) {
            viewModel.addNewToDo(
                binding.titleInAddTodo.text.toString(),
                binding.descriptionInAddTodo.text.toString(),
            )
            val action = AddToDoFragmentDirections.actionAddToDoFragmentToToDoListFragment()
            findNavController().navigate(action)
        }
    }


    private fun updateTodo() {
        if (isEntryValid()) {
            viewModel.updateToDo(
                this.navigationArgs.toDoId,
                this.binding.titleInAddTodo.text.toString(),
                this.binding.descriptionInAddTodo.text.toString(),
            )
            val action = AddToDoFragmentDirections.actionAddToDoFragmentToToDoListFragment()
            findNavController().navigate(action)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.toDoId
        if (id > 0) {
            viewModel.retrieveToDo(id).observe(this.viewLifecycleOwner) { selectedTodo ->
                todo = selectedTodo
                bind(todo)
            }
        } else {
            binding.submitToDo.setOnClickListener {
                addNewToDo()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}
