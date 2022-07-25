package cnovaez.dev.notebookmvvm.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.databinding.FragmentMainBinding
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.ui.adapter.notes.NotesAdapter
import cnovaez.dev.notebookmvvm.ui.viewmodels.logs.LogsViewModel
import cnovaez.dev.notebookmvvm.ui.viewmodels.notes.NotesViewModel
import cnovaez.dev.notebookmvvm.ui.views.components.DialogBottomSheetLogs
import cnovaez.dev.notebookmvvm.ui.views.components.DialogNoteDetails
import cnovaez.dev.notebookmvvm.ui.views.components.DialogScheduleNotification
import cnovaez.dev.notebookmvvm.utils.misc.SessionValues
import cnovaez.dev.notebookmvvm.utils.types.ActionType
import cnovaez.dev.notebookmvvm.utils.types.NoteActionType
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainNotesListFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentMainBinding? = null;
    private val binding get() = _binding!!;

    private val notesViewModel: NotesViewModel by viewModels()
    private val logsViewModel: LogsViewModel by viewModels()

    companion object {
        lateinit var mNavController: NavController;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        logsViewModel.loading.observe(this) { value ->
            binding.isLoadingPb.visibility = if(!value) View.GONE else View.VISIBLE
        }
        logsViewModel.logsModel.observe(this) { logs ->
            DialogBottomSheetLogs().show(parentFragmentManager, "logs")
        }

    }

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
        binding.openLogsSheetFab.setOnClickListener(this)
    }

    /**
     * This function will be called when a item from the recycler is clicked.
     */
    private fun onItemSelected(note: Note) {
        when (note.action!!) {
            ActionType.DETAILS -> displayDetailsCard(note)
            ActionType.DELETE
            -> {
                showDeleteSnackBar(note)
                deleteNote(note)
            }
            ActionType.NOTIFICATION -> {

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

    private fun showDeleteSnackBar(note: Note) {
        val snack = Snackbar.make(binding.noteListRv, "Note deleted", Snackbar.LENGTH_LONG)
        snack.setAction("UNDO", View.OnClickListener {
            notesViewModel.insertNote(note, NoteActionType.UPDATE)
        })
        snack.show()
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


    private fun verifyIfAppWasOpenFromNotification() {
        val noteId = SessionValues.noteId;
        // this.toastMsg(noteId.toString())
        if (noteId != -1) {
            notesViewModel.singleNoteModel.observe(this) { note ->
                displayDetailsCard(note)
            }
            SessionValues.noteId = -1;
            notesViewModel.loadNote(noteId)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.addNoteFab.id -> {
                mNavController.navigate(R.id.newNoteFragment);
            }
            binding.openLogsSheetFab.id -> {
                logsViewModel.getAllLogs();
            }
        }
    }


}