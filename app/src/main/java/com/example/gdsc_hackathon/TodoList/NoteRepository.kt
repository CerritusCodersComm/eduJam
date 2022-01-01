package com.example.gdsc_hackathon.TodoList

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class NoteRepository(application: Application?) {
    private val noteDao: NoteDao
    val allNotes: LiveData<List<Note?>?>?

    fun insert(note: Note?) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note?) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note?) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(noteDao).execute()
    }

    private class InsertNoteAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Note?, Void?, Void?>() {
        override fun doInBackground(vararg notes: Note?): Void? {
            noteDao.insert(notes[0])
            return null
        }
    }

    private class UpdateNoteAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Note?, Void?, Void?>() {
        override fun doInBackground(vararg params: Note?): Void? {
            noteDao.update(params[0])
            return null
        }
    }

    private class DeleteNoteAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Note?, Void?, Void?>() {
        override fun doInBackground(vararg notes: Note?): Void? {
            noteDao.delete(notes[0])
            return null
        }
    }

    private class DeleteAllNotesAsyncTask(private val noteDao: NoteDao) :
        AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg params: Void?): Void? {
            noteDao.deleteAllNotes()
            return null
        }
    }

    init {
        val database = NoteDatabase.getInstance(application!!)
        noteDao = database!!.noteDao()
        allNotes = noteDao.allNotes
    }
}
