package com.example.gdsc_hackathon.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.activities.AddToDoItem
import com.example.gdsc_hackathon.adapters.ToDoListAdapter
import com.example.gdsc_hackathon.databinding.FragmentTodoListBinding



class TodoListFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentTodoListBinding
    private lateinit var adapter: ToDoListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        binding.floatingActionButtonToAddItemList.setOnClickListener(this)
        setUpRecycleView()
        return binding.root

    }

    private fun setUpRecycleView() {
        adapter = activity?.let { ToDoListAdapter(it) }!!
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
                view?.let {
                    adapter.removeItem(
                        viewHolder.absoluteAdapterPosition,
                        it

                    )
                }

            }
        }).attachToRecyclerView(binding.recycleViewForTodoList)
        binding.recycleViewForTodoList.setHasFixedSize(true)
        binding.recycleViewForTodoList.layoutManager = LinearLayoutManager(activity)
        binding.recycleViewForTodoList.adapter = adapter

    }

    override fun onClick(p0: View?) {

        when (p0?.id) {
            com.example.gdsc_hackathon.R.id.floating_action_button_to_add_item_list -> {

                activity?.let {
                    val intent = Intent(it, AddToDoItem::class.java)
                    it.startActivity(intent)
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        setUpRecycleView()
    }

}