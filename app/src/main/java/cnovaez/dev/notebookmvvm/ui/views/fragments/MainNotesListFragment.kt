package cnovaez.dev.notebookmvvm.ui.views.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.databinding.FragmentMainBinding
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.ui.adapter.NotesAdapter
import cnovaez.dev.notebookmvvm.ui.viewmodels.NotesViewModel
import cnovaez.dev.notebookmvvm.ui.views.components.DialogNoteDetails
import cnovaez.dev.notebookmvvm.ui.views.components.DialogScheduleNotification
import cnovaez.dev.notebookmvvm.utils.ext.toastMsg
import cnovaez.dev.notebookmvvm.utils.misc.SessionValues
import cnovaez.dev.notebookmvvm.utils.types.ActionType
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainNotesListFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentMainBinding? = null;
    private val binding get() = _binding!!;

    private lateinit var mNavController: NavController;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.root
    }

    private val notesViewModel: NotesViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        notesViewModel.onCreate()

        notesViewModel.noteModel.observe(this) { notes ->
            //do somenting with the loaded notes.
            initRecyclerView(notes)
        }

        notesViewModel.isLoading.observe(this) {
            binding.noteListRv.visibility = if (it) View.GONE else View.VISIBLE
            handleLoadingDisplay(it)
        }
        notesViewModel.emptyNotes.observe(this) { empty ->
            var message = if (empty) "No notes :(" else ""
            binding.noNotestv.text = message
        }

        verifyIfAppWasOpenFromNotification()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = Navigation.findNavController(view);
        initializeListener()
    }

    private fun initRecyclerView(notes: List<Note>) {
        val recyclerView = binding.noteListRv;
        recyclerView.layoutManager = LinearLayoutManager(activity);
        recyclerView.adapter = NotesAdapter(notes) { note -> onItemSelected(note) };
    }

    private fun initializeListener() {
        binding.addNoteFab.setOnClickListener(this)
    }

    private fun verifyIfAppWasOpenFromNotification() {
        val noteId = SessionValues.noteId;
       // this.toastMsg(noteId.toString())
        if (noteId != -1) {
            notesViewModel.singleNoteModel.observe(this) { note ->
                displayDetailsCard(note)
            }
            notesViewModel.loadNote(noteId)
        }
    }

    /**
     * This function will be called when a item from the recycler is clicked.
     */
    private fun onItemSelected(note: Note) {
        when (note.action!!) {
            ActionType.DETAILS -> displayDetailsCard(note)
            ActionType.DELETE
            -> deleteNote(note)
            ActionType.NOTIFICATION -> {
                this.toastMsg("Opening notification dialog")
                DialogScheduleNotification(
                    activity!!.baseContext,
                    note
                ).show(
                    parentFragmentManager,
                    "dialog"
                )
            }
        }
    }

    private fun displayDetailsCard(note: Note) {
        DialogNoteDetails(note).show(parentFragmentManager, "dialog")
    }

    private fun deleteNote(note: Note) {
        notesViewModel.deleteNote(note)
    }


    private fun handleLoadingDisplay(it: Boolean) {
        if (SessionValues.firstRun) {
            binding.isLoadingSm.visibility = if (!it) View.GONE else View.VISIBLE
            if (!it) {
                SessionValues.firstRun = false
            }
        } else {
            binding.isLoadingPb.visibility = if (!it) View.GONE else View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.addNoteFab.id -> {
                mNavController.navigate(R.id.newNoteFragment);
            }
        }
    }


}