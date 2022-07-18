package cnovaez.dev.notebookmvvm.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.databinding.NoteCardBinding
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.model.color
import cnovaez.dev.notebookmvvm.ui.views.components.DialogScheduleNotification
import cnovaez.dev.notebookmvvm.utils.ext.color
import cnovaez.dev.notebookmvvm.utils.types.ActionType
import kotlin.coroutines.coroutineContext

class NotesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val binding = NoteCardBinding.bind(view)

    fun render(noteModel: Note, onClickListener: (Note) -> Unit) {
        binding.titleTv.text = noteModel.title
        binding.dateTv.text = noteModel.date
        binding.iconIv.setImageResource(noteModel.icon)
        binding.noteCl.setBackgroundColor(view.color(noteModel.color()))

        binding.notificationScheduleCV.setOnClickListener {
            noteModel.action = ActionType.NOTIFICATION
            onClickListener(noteModel)
        }
        binding.noteCl.setOnClickListener {
            noteModel.action = ActionType.DETAILS
            onClickListener(noteModel)
        }
        binding.deleteNoteBtn.setOnClickListener {
            noteModel.action = ActionType.DELETE
            onClickListener(noteModel)
        }
    }


}