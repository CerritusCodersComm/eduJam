package com.example.gdsc_hackathon.viewmodels


import androidx.lifecycle.*
import com.example.gdsc_hackathon.dao.ToDoDao
import com.example.gdsc_hackathon.dataModel.ToDo

import kotlinx.coroutines.launch


class ToDoListViewModel(private val toDoDao: ToDoDao) : ViewModel() {

    val allItems: LiveData<List<ToDo>> = toDoDao.getToDoList().asLiveData()

    fun updateToDo(
        toDoId: Int,
        toDoTitle: String,
        toDoDescription: String,
    ) {
        val updatedToDo = getUpdatedToDoEntry(toDoId, toDoTitle, toDoDescription)
        updateToDo(updatedToDo)
    }


    private fun updateToDo(item: ToDo) {
        viewModelScope.launch {
            toDoDao.update(item)
        }
    }




    fun addNewToDo( toDoTitle: String,
                    toDoDescription: String,) {
        val newItem = getNewToDoEntry(toDoTitle, toDoDescription)
        insertToDo(newItem)
    }


    private fun insertToDo(item: ToDo) {
        viewModelScope.launch {
            toDoDao.insert(item)
        }
    }


    fun deleteToDo(item: ToDo) {
        viewModelScope.launch {
            toDoDao.delete(item)
        }
    }


    fun retrieveToDo(id: Int): LiveData<ToDo> {
        return toDoDao.getToDoList(id).asLiveData()
    }


    fun isEntryValid(     toDoTitle: String,
                          toDoDescription: String,): Boolean {
        if (toDoTitle.isBlank() || toDoDescription.isBlank() ) {
            return false
        }
        return true
    }


    private fun getNewToDoEntry(
        toDoTitle: String,
        toDoDescription: String,
    ): ToDo {
        return ToDo(
            title = toDoTitle,
            description = toDoDescription,
        )
    }


    private fun getUpdatedToDoEntry(
        toDoId: Int,
        toDoTitle: String,
        toDoDescription: String,
    ): ToDo {
        return ToDo(
            id = toDoId,
            title = toDoTitle,
            description = toDoDescription,
        )
    }


}


class ToDoListViewModelFactory(private val toDoDao: ToDoDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ToDoListViewModel(toDoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
