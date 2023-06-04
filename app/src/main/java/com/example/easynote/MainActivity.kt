package com.example.easynote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),INotesRVAdapter {
    lateinit var viewmodel: Note_Viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerview:RecyclerView=findViewById(R.id.recyclerView)
        recyclerview.layoutManager=LinearLayoutManager(this)
        val adapter=Adapter(this)
        recyclerview.adapter=adapter

        viewmodel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(Note_Viewmodel::class.java)
        viewmodel.allnotes.observe(this,Observer{list->
            list?.let {
                adapter.updateList(it)
            }

        })
    }

    override fun OnItemClicked(note: Note) {
        viewmodel.deleteNote(note)
        Toast.makeText(this,"${note.text}deleted",Toast.LENGTH_SHORT).show()

    }

    fun submit(view: View) {
        val input=findViewById<EditText>(R.id.enter_note)
        val noteText = input.text.toString()
        if (noteText.isNotEmpty()){
            viewmodel.insertNode(Note(noteText))
            Toast.makeText(this,"$noteText inserted",Toast.LENGTH_SHORT).show()
        }
    }
}