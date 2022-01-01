package com.example.gdsc_hackathon.new_todo_list

import android.content.Intent
import android.os.Bundle

import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R


class AddTodoActivity: AppCompatActivity(), RadioGroup.OnCheckedChangeListener{

    private lateinit var todoDatabase: TodoListDatabase
    private lateinit var radioGroup: RadioGroup
    private lateinit var add_todo: Button
    private lateinit var title_ed: EditText
    private lateinit var detail_ed: EditText
    private var priority = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        add_todo = findViewById(R.id.add_todo)
        title_ed = findViewById(R.id.title_ed)
        detail_ed = findViewById(R.id.detail_ed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        todoDatabase = TodoListDatabase.getInstance(this)!!
        radioGroup.setOnCheckedChangeListener(this)

        val title = intent.getStringExtra("title")
        if (title == null || title == ""){
            add_todo.setOnClickListener{
                val todo = Todo(title_ed.text.toString(), priority)
                todo.detail = detail_ed.text.toString()
                todoDatabase.getTodoDao().saveTodo(todo)
                finish()
            }
        }else{
            add_todo.text = getString(R.string.update)
            val tId = intent.getIntExtra("tId", 0)
            title_ed.setText(title)
            add_todo.setOnClickListener {
                val todo = Todo(title_ed.text.toString(), priority, tId)
                todo.detail = detail_ed.text.toString()
                todoDatabase.getTodoDao().updateTodo(todo)
                finish()
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (checkedId == R.id.medium){
            priority = 2
        }else if (checkedId == R.id.high) {
            priority = 3
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            startActivity(Intent(this, TodoActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}