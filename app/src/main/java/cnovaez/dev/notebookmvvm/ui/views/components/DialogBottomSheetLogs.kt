package cnovaez.dev.notebookmvvm.ui.views.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity
import cnovaez.dev.notebookmvvm.databinding.LogsDialogBinding
import cnovaez.dev.notebookmvvm.domain.model.Log
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.model.toEntity
import cnovaez.dev.notebookmvvm.ui.adapter.logs.LogsAdapter
import cnovaez.dev.notebookmvvm.ui.viewmodels.logs.LogsViewModel
import cnovaez.dev.notebookmvvm.utils.types.LogActions
import cnovaez.dev.notebookmvvm.utils.types.NoteActionType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogBottomSheetLogs() : BottomSheetDialogFragment() {


    private var _binding: LogsDialogBinding? = null
    private val binding get() = _binding!!

    private val logsViewModel: LogsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LogsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        logsViewModel.logsModel.observe(this) { logs ->
            initRecyclerView(logs)
            binding.noLogstv.visibility = if (logs.size > 0) View.GONE else View.VISIBLE
            // binding.cardRecyclerLogsCv.visibility = if (logs.size == 0) View.GONE else View.VISIBLE
        }

        logsViewModel.getAllLogs()
    }
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        super.onCreateDialog(savedInstanceState)
//        binding = LogsDialogBinding.inflate(layoutInflater)
//        val builder = AlertDialog.Builder(requireActivity())
//        builder.setView(binding.root)
//
//
//
//        //for transparent background
//        val dialog = builder.create()
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//        return dialog
//    }

    fun initRecyclerView(logs: List<Log>) {
        val recyclerView = binding.logListRv
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = LogsAdapter(logs, { onItemSelected(it) })
    }

    fun onItemSelected(log: Log) {
        when (log.logAction) {
            LogActions.DELETE -> {
                showDeleteSnackBar(log.toEntity())
                logsViewModel.deleteLogEntry(log.toEntity())
            }
        }
    }

    private fun showDeleteSnackBar(logEntity: LogEntity) {
        val snack = Snackbar.make(binding.logListRv, "Log deleted", Snackbar.LENGTH_LONG)
        snack.setAction("UNDO", View.OnClickListener {
            logsViewModel.insertNewLog(logEntity);
        })
        snack.show()
    }

}