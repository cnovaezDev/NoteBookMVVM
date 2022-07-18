package cnovaez.dev.notebookmvvm.ui.views.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import cnovaez.dev.notebookmvvm.databinding.StateMessageBinding


class DialogNotification(private val message: String) : DialogFragment() {

    private lateinit var binding: StateMessageBinding

    //TODO Needs a ViewModel so his view will be updated rather that duplicated
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        binding = StateMessageBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.stateDialogDismissBtn.setOnClickListener {
            this.dismiss()
        }
        binding.savingStateTv.text = message

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }
}