package cnovaez.dev.notebookmvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.domain.model.Note

class NotesAdapter(private val notes: List<Note>, private val onClickListener:(Note)-> Unit) : RecyclerView.Adapter<NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        return NotesViewHolder(layoutInflater.inflate(R.layout.note_card, parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = notes[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = notes.size
}