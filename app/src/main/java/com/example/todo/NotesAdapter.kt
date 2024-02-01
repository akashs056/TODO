package com.example.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val context: Context, private val listener: INotesRVAdapter) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val list=ArrayList<Note>()

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val text=itemView.findViewById<TextView>(R.id.textView)
        val delete=itemView.findViewById<ImageView>(R.id.delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder=NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        viewHolder.delete.setOnClickListener{
            listener.onItemClicked(list[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote=list[position]
        holder.text.text=currentNote.text
    }
    fun updateList(newList :List<Note>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}

interface  INotesRVAdapter {
    fun onItemClicked(note: Note){

    }
}