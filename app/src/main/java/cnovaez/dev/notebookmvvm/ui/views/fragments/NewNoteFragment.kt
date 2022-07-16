package cnovaez.dev.notebookmvvm.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.databinding.ActivityMainBinding
import cnovaez.dev.notebookmvvm.databinding.FragmentMainBinding
import cnovaez.dev.notebookmvvm.databinding.FragmentNewNoteBinding
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.ui.adapter.NotesAdapter
import cnovaez.dev.notebookmvvm.ui.viewmodels.NewNoteViewModel
import cnovaez.dev.notebookmvvm.ui.viewmodels.NotesViewModel
import cnovaez.dev.notebookmvvm.ui.views.components.DialogInsertionState
import cnovaez.dev.notebookmvvm.utils.types.PriorityTypes
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Time
import java.sql.Timestamp
import java.time.Instant
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class NewNoteFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentNewNoteBinding? = null;
    private val binding get() = _binding!!;

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
        binding.saveNoteFoa.setOnClickListener(this)
        newNoteViewModel.saving.observe(this) {
            showStateDialog(it.name)
        }
    }

    private fun harvestNoteData(): Note {
        return Note(
            id = -1,
            title = binding.titleEt.text.toString(),
            description = binding.contentEt.toString(),
            date = DateTimeFormatter.ISO_INSTANT.format(Instant.now()),
            icon = R.drawable.ic_cake,
            priority = PriorityTypes.MEDIUM,
            image_data = null,
            voice_data = null,
            text_data = null
        )
    }

    private fun showStateDialog(message: String) {
        DialogInsertionState(message).show(parentFragmentManager, "dialog")
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.saveNoteFoa.id -> newNoteViewModel.insertNote(harvestNoteData())
        }
    }


}