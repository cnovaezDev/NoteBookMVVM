package cnovaez.dev.notebookmvvm.ui.views.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import cnovaez.dev.notebookmvvm.databinding.NoteDetailsBinding
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.model.color

class DialogNoteDetails(private val note: Note) : DialogFragment() {

    private lateinit var binding: NoteDetailsBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        binding = NoteDetailsBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.cardDetailCl.setBackgroundResource(note.color())
        binding.detailCardTitleTv.text = note.title;
        binding.detailCardContentTv.text = note.description;
        binding.noteIconIv.setImageResource(note.icon);
        binding.closeDetailBtn.setOnClickListener { this.dismiss() }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

}