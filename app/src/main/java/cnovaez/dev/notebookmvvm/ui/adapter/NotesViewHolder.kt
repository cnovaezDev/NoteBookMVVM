package cnovaez.dev.notebookmvvm.ui.adapter

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.model.color
import cnovaez.dev.notebookmvvm.utils.color

class NotesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val title: TextView = view.findViewById<TextView>(R.id.title_tv);
    val date: TextView = view.findViewById<TextView>(R.id.date_tv);
    val mainIcon: ImageView = view.findViewById<ImageView>(R.id.icon_iv);
    val dateIcon: ImageView = view.findViewById<ImageView>(R.id.iconDate_iv);
    val noteCard: ConstraintLayout = view.findViewById<ConstraintLayout>(R.id.note_cl);

    fun render(noteModel: Note) {
        title.text = noteModel.title
        date.text = noteModel.date
        mainIcon.setImageResource(noteModel.icon)
        dateIcon.setImageResource(R.drawable.ic_calendar)
        noteCard.setBackgroundColor(view.color(noteModel.color()))
    }
}