package cnovaez.dev.notebookmvvm.ui.views.components

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.databinding.NoteIconsBinding

class DialogIcons(
    private val onSubmitClickListener: (Int) -> Unit
) : DialogFragment() {

    private lateinit var binding: NoteIconsBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        binding = NoteIconsBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        initializeOnClickListener()

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    fun initializeOnClickListener() {
        binding.clipv.setOnClickListener {
            onSubmitClickListener.invoke(R.drawable.clip_ic)
        }
        binding.batteryIv.setOnClickListener {
            onSubmitClickListener.invoke(R.drawable.battery_ic)
        }
        binding.hatIv.setOnClickListener {
            onSubmitClickListener.invoke(R.drawable.hat_ic)
        }
        binding.settignsIv.setOnClickListener {
            onSubmitClickListener.invoke(R.drawable.settings_ic)
        }
        binding.graphIv.setOnClickListener {
            onSubmitClickListener.invoke(R.drawable.graph_ic)
        }
    }

}