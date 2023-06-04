package com.example.easynote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Note_Viewmodel(application: Application) : AndroidViewModel(application) {
    private val repository:Note_Repository
    val allnotes:LiveData<List<Note>>
    init {
        val dao=Note_Database.getDatabase(application).getNoteDao()
         repository=Note_Repository(dao)
        allnotes=repository.allnotes
    }
    fun deleteNote(note: Note){
    val delete=viewModelScope.launch (Dispatchers.IO){
        repository.delete(note)
    }}

        fun insertNode(note: Note){
            val insert=viewModelScope.launch (Dispatchers.IO){
                repository.insert(note)
            }
        }
}