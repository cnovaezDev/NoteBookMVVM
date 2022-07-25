package cnovaez.dev.notebookmvvm.ui.adapter.logs

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity
import cnovaez.dev.notebookmvvm.databinding.LogCardBinding
import cnovaez.dev.notebookmvvm.domain.model.Log
import cnovaez.dev.notebookmvvm.utils.ext.toHtlmString
import cnovaez.dev.notebookmvvm.utils.types.LogActions
import cnovaez.dev.notebookmvvm.utils.types.NoteActionType

class LogsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = LogCardBinding.bind(view)
    fun render(log: Log,onClickListener: (Log) -> Unit) {
        binding.actionTv.text = toHtlmString(log.action_desc)
        binding.actionDateTv.text = log.action_date
        binding.iconActionIv.setImageResource(setActionIcon(log.action_type))
        if (log.action_type == NoteActionType.SCHEDULE_NOTIFICATION) {
            binding.dateNotiTv.text = log.notification_date
            binding.notificationLl.visibility = View.VISIBLE;
        }
        binding.deleteLogBtn.setOnClickListener{
            log.logAction = LogActions.DELETE
            onClickListener(log)
        }

    }

    private fun setActionIcon(actionType: NoteActionType): Int {
        return when (actionType) {
            NoteActionType.NEW -> R.drawable.new_note_action_ic
            NoteActionType.UPDATE -> R.drawable.edit_note_action_ic
            NoteActionType.DELETE -> R.drawable.delete_note_action_ic
            NoteActionType.SCHEDULE_NOTIFICATION -> R.drawable.schedule_notification_ic
        }
    }


}