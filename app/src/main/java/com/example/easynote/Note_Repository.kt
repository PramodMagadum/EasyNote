package com.example.easynote

import androidx.lifecycle.LiveData

class Note_Repository(private val notesDao: Notes_DAO) {

    val allnotes:LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note:Note){
        notesDao.insert(note)
    }
    suspend fun delete(note:Note){
        notesDao.delete(note)
    }
}