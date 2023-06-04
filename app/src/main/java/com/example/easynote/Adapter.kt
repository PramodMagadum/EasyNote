package com.example.easynote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val listener: MainActivity): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val allnote = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val viewholder=ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_viewholder,parent,false))
       viewholder.delete.setOnClickListener{
        listener.OnItemClicked(allnote[viewholder.adapterPosition])
       }
        return viewholder
    }

    override fun getItemCount(): Int {
return allnote.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentNote = allnote[position]
        (holder as ViewHolder).note.text = currentNote.text
    }
    class ViewHolder( itemView: View):RecyclerView.ViewHolder(itemView){
        val note:TextView=itemView.findViewById(R.id.note)
        val delete:ImageView=itemView.findViewById(R.id.delete_image)
    }
    fun updateList(noteList:List<Note>){
       allnote.clear()
        allnote.addAll(noteList)

        notifyDataSetChanged()
    }
}
interface INotesRVAdapter{
    fun OnItemClicked(note: Note)


}