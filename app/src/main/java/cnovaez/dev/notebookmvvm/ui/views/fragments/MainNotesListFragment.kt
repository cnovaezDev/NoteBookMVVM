package cnovaez.dev.notebookmvvm.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.databinding.ActivityMainBinding
import cnovaez.dev.notebookmvvm.databinding.FragmentMainBinding
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.ui.adapter.NotesAdapter
import cnovaez.dev.notebookmvvm.ui.viewmodels.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint

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
            binding.loadingPb.isVisible = it;
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mNavController = Navigation.findNavController(view);
        initializeListener()
    }

    private fun initRecyclerView(notes: List<Note>) {
        val recyclerView = binding.noteListRv;
        recyclerView.layoutManager = LinearLayoutManager(activity);
        recyclerView.adapter = NotesAdapter(notes);
    }

    private fun initializeListener() {
        binding.addNoteFab.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            binding.addNoteFab.id -> {
                mNavController.navigate(R.id.newNoteFragment);
            }
        }
    }

}