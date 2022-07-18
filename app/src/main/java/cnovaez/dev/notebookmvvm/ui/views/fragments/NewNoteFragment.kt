package cnovaez.dev.notebookmvvm.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.databinding.FragmentNewNoteBinding
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.model.imgPriority
import cnovaez.dev.notebookmvvm.ui.viewmodels.NewNoteViewModel
import cnovaez.dev.notebookmvvm.ui.views.components.DialogIcons
import cnovaez.dev.notebookmvvm.ui.views.components.DialogNotification
import cnovaez.dev.notebookmvvm.utils.misc.SessionValues
import cnovaez.dev.notebookmvvm.utils.types.NoteActionType
import cnovaez.dev.notebookmvvm.utils.types.PriorityTypes
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.Instant

@AndroidEntryPoint
class NewNoteFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentNewNoteBinding? = null;
    private val binding get() = _binding!!;
    private var notePriority = PriorityTypes.LOW;
    private var imageResource: Int = R.drawable.clip_ic
    private lateinit var fm: FragmentManager
    private var noteId: Int = -1;

    private val newNoteViewModel: NewNoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadNoteForEdition()
        binding.saveNoteFoa.setOnClickListener(this)
        binding.priorityIv.setOnClickListener(this)
        binding.noteIconIv.setOnClickListener(this)
        fm = activity!!.supportFragmentManager
        newNoteViewModel.saving.observe(this) {
            fm.popBackStack()
        }
    }

    private fun loadNoteForEdition() {
        noteId = SessionValues.noteId;
        if (SessionValues.noteId != -1) {
            newNoteViewModel.noteModel.observe(this) { note ->
                binding.titleEt.setText(note.title)
                binding.contentEt.setText(note.description)
                binding.noteIconIv.setImageResource(note.icon)
                binding.priorityIv.setImageResource(note.imgPriority())
            }
            newNoteViewModel.loadNote(noteId)
            SessionValues.noteId = -1;
        }
    }

    private fun harvestNoteData() {
        var currNote: Note?;
        var action = NoteActionType.NEW;
        if (noteId != -1) {
            currNote = Note(
                id = noteId,
                title = binding.titleEt.text.toString(),
                description = binding.contentEt.text.toString(),
                date = getCurrentDate(),
                icon = imageResource,
                priority = notePriority
            )
            action = NoteActionType.UPDATE;
        } else {
            currNote = Note(
                title = binding.titleEt.text.toString(),
                description = binding.contentEt.text.toString(),
                date = getCurrentDate(),
                icon = imageResource,
                priority = notePriority
            )
        }
        newNoteViewModel.insertNote(currNote, action)

    }

    private fun validateNeededFields(): Boolean {
        var valid = true
        if (binding.titleEt.text.isNullOrEmpty()) {
            binding.titleIl.error = "Title can't be empty"
            valid = false
        } else {
            binding.titleIl.error = null
        }
        return valid;
    }

    private fun showStateDialog(message: String) {
        DialogNotification(message).show(parentFragmentManager, "dialog")
    }

    fun updateNotePriority() {
        when (notePriority) {
            PriorityTypes.LOW -> {
                notePriority = PriorityTypes.MEDIUM
                binding.priorityIv.setImageResource(R.drawable.prio_med)
            }
            PriorityTypes.MEDIUM -> {
                notePriority = PriorityTypes.HIGH
                binding.priorityIv.setImageResource(R.drawable.prio_high)
            }
            PriorityTypes.HIGH -> {
                notePriority = PriorityTypes.LOW
                binding.priorityIv.setImageResource(R.drawable.prio_low)
            }
        }

    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
        val netDate = Instant.now().toEpochMilli()
        return sdf.format(netDate)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.saveNoteFoa.id -> {
                if (validateNeededFields()) {
                    harvestNoteData()
                }
            }
            binding.priorityIv.id -> updateNotePriority()
            binding.noteIconIv.id -> {
                DialogIcons(
                    onSubmitClickListener = {
                        binding.noteIconIv.setImageResource(it)
                        imageResource = it
                    }).show(parentFragmentManager, "dialog")
            }
        }
    }


}