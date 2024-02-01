package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    private lateinit var viewModel: NoteViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NotesAdapter(this,this)
        binding.recyclerView.adapter=adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })

        binding.submit.setOnClickListener {
            val text=binding.input.text.toString()
            if (text.isNotEmpty()){
                viewModel.addNote(Note(text))
                binding.input.text=null
                Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"Note deleted",Toast.LENGTH_SHORT).show()
    }
}