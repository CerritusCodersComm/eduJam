package com.example.gdsc_hackathon.new_todo_list

import androidx.room.*

@Entity(tableName = "todo")
class Todo(
    @ColumnInfo(name = "todo_title")
    var title:String = "",
    @ColumnInfo(name = "todo_priority")
    var priority: Int = 0,
    @PrimaryKey(autoGenerate = true) var tId: Int = 0){
    var detail: String = ""
}