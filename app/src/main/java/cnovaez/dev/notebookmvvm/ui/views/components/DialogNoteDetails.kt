package cnovaez.dev.notebookmvvm.ui.views.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.databinding.NoteDetailsDialogBinding
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.model.color
import cnovaez.dev.notebookmvvm.ui.views.fragments.MainNotesListFragment
import cnovaez.dev.notebookmvvm.utils.ext.toastMsg
import cnovaez.dev.notebookmvvm.utils.misc.SessionValues

class DialogNoteDetails(private val note: Note) : DialogFragment() {

    private lateinit var binding: NoteDetailsDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        binding = NoteDetailsDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.cardDetailCl.setBackgroundResource(note.color())
        binding.detailCardTitleTv.text = note.title;
        binding.detailCardContentTv.text = note.description;
        binding.noteIconIv.setImageResource(note.icon);
        binding.closeDetailBtn.setOnClickListener { this.dismiss() }
        binding.closeIconIv.setOnClickListener { this.dismiss() }
        binding.editDetailBtn.setOnClickListener {
            SessionValues.noteId = note.id
           // this.toastMsg(note.id.toString())
            MainNotesListFragment.mNavController.navigate(R.id.newNoteFragment)
            this.dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

}