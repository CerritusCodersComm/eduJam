package com.example.gdsc_hackathon.TodoList

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TodoListActivity : AppCompatActivity(), NoteAdapter.ItemOnClickListener {
    private var noteViewModel: NoteViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)
        val floatingActionButton = findViewById<View>(R.id.button_add_note) as FloatingActionButton
        floatingActionButton.setOnClickListener {
            val intent = Intent(this@TodoListActivity, AddEditNote::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val adapter = NoteAdapter()
        recyclerView.adapter = adapter
        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        noteViewModel!!.allNotes?.observe(this) { notes: List<Note?>? -> adapter.setNotes(notes ) }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel?.delete(adapter.getNoteAt(viewHolder.absoluteAdapterPosition))
                Toast.makeText(this@TodoListActivity, "Note Deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
//        adapter.setOnClickListener(object : itemOnClickListener(), NoteAdapter.ItemOnClickListener {
//            fun onItemClick(note: Note) {
//                val intent = Intent(this@MainActivity, AddEditNote::class.java)
//                intent.putExtra(AddEditNote.EXTRA_ID, note.id)
//                intent.putExtra(AddEditNote.EXTRA_TITLE, note.title)
//                intent.putExtra(AddEditNote.EXTRA_DESCRIPTION, note.description)
//                intent.putExtra(AddEditNote.EXTRA_PRIORITY, note.priority)
//                startActivityForResult(intent, EDIT_NOTE_REQUEST)
//            }
//        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            val title = data!!.getStringExtra(AddEditNote.EXTRA_TITLE)
            val description = data.getStringExtra(AddEditNote.EXTRA_DESCRIPTION)
            val priority = data.getIntExtra(AddEditNote.EXTRA_PRIORITY, 1)
            val note = Note(title.toString(), description.toString(), priority)
            noteViewModel?.insert(note)
            Toast.makeText(this, "Note is created", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            val id = intent.getIntExtra(AddEditNote.EXTRA_ID, -1)
            if (id == -1) {
                Toast.makeText(this, "Note Cannot be Updated", Toast.LENGTH_SHORT).show()
                return
            }
            val title = data!!.getStringExtra(AddEditNote.EXTRA_TITLE)
            val description = data.getStringExtra(AddEditNote.EXTRA_DESCRIPTION)
            val priority = data.getIntExtra(AddEditNote.EXTRA_PRIORITY, 1)
            val note = Note(title.toString(), description.toString(), priority)
            note.id = id
            noteViewModel?.update(note)
        } else {
            Toast.makeText(this, "Note not created", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel?.deleteAllNotes()
                Toast.makeText(this, "All notes Deleted", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }

    override fun onItemClick(note: Note?) {
        val intent = Intent(this, AddEditNote::class.java)
        intent.putExtra(AddEditNote.EXTRA_ID, note?.id)
        intent.putExtra(AddEditNote.EXTRA_TITLE, note?.title)
        intent.putExtra(AddEditNote.EXTRA_DESCRIPTION, note?.description)
        intent.putExtra(AddEditNote.EXTRA_PRIORITY, note?.priority)
        startActivityForResult(intent, EDIT_NOTE_REQUEST)
    }
}