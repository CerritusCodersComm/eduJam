package com.example.gdsc_hackathon.new_todo_list

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//You can also check out type converters
@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoListDatabase: RoomDatabase(){

    /**
     * This is an abstract method that returns a dao for the Db
     * */
    abstract fun getTodoDao(): TodoDao

    /**
     * A singleton design pattern is used to ensure that the database instance created is one
     * */
    companion object {

        @Volatile
        private var INSTANCE: TodoListDatabase? = null

        private const val databaseName = "tododatabase"
        private var todoListDatabase: TodoListDatabase? = null

        fun getInstance(context: Context): TodoListDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoListDatabase::class.java,
                        databaseName
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

//                if (todoListDatabase == null) {
//                    todoListDatabase = Room.databaseBuilder(
//                        context,
//                        TodoListDatabase::class.java,
//                        databaseName
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                }
//                return todoListDatabase
//            }
        }
    }
}