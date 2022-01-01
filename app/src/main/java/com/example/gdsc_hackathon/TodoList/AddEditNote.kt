package com.example.gdsc_hackathon.TodoList

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R


class AddEditNote : AppCompatActivity() {
    private var editTextTitle: EditText? = null
    private var editTextDescription: EditText? = null
    private var numberPickerPriority: NumberPicker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        editTextTitle = findViewById<View>(R.id.edit_text_title) as EditText
        editTextDescription = findViewById<View>(R.id.edit_text_description) as EditText
        numberPickerPriority = findViewById<View>(R.id.number_picker_priority) as NumberPicker
        numberPickerPriority!!.minValue = 1
        numberPickerPriority!!.maxValue = 10
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
        val intent = intent
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Note"
            editTextDescription!!.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            editTextTitle!!.setText(intent.getStringExtra(EXTRA_TITLE))
            numberPickerPriority!!.value =
                intent.getIntExtra(EXTRA_PRIORITY, 1)
        } else {
            title = "Add Note"
        }
    }

    private fun saveNote() {
        val title = editTextTitle!!.text.toString()
        val description = editTextDescription!!.text.toString()
        val priority = numberPickerPriority!!.value
        if (title.trim { it <= ' ' }.isEmpty() || description.trim { it <= ' ' }.isEmpty()) {
            Toast.makeText(this, "Please Enter Title and Description", Toast.LENGTH_SHORT).show()
            return
        }
        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, description)
        data.putExtra(EXTRA_PRIORITY, priority)
        val id = intent.getIntExtra(EXTRA_ID, -1)
        if (id != -1) {
            data.putExtra(EXTRA_ID, id)
        }
        setResult(RESULT_OK, data)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_ID = "com.example.noteapp.EXTRA_ID"
        const val EXTRA_TITLE = "com.example.noteapp.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.example.noteapp.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.example.noteapp.EXTRA_PRIORITY"
    }
}