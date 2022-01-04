package com.example.gdsc_hackathon.dao


import kotlinx.coroutines.flow.Flow
import com.example.gdsc_hackathon.dataModel.ToDo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update



@Dao
interface ToDoDao {

    @Query("SELECT * from ToDo ORDER BY title ASC")
    fun getToDoList(): Flow<List<ToDo>>

    @Query("SELECT * from ToDo WHERE id = :id")
    fun getToDoList(id: Int): Flow<ToDo>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing ToDoList into the database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(toDo: ToDo)

    @Update
    suspend fun update(toDo: ToDo)

    @Delete
    suspend fun delete(toDo: ToDo)
}




