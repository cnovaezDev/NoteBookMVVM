package cnovaez.dev.notebookmvvm.ui.views.components

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity
import cnovaez.dev.notebookmvvm.databinding.NotificationScheduleDialogBinding
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.ui.viewmodels.logs.ScheduleNotificationViewModel
import cnovaez.dev.notebookmvvm.utils.ext.getCurrentDate
import cnovaez.dev.notebookmvvm.utils.misc.*
import cnovaez.dev.notebookmvvm.utils.types.NoteActionType
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@AndroidEntryPoint
class DialogScheduleNotification(private val applicationContext: Context, private val note: Note) :
    DialogFragment() {
    private lateinit var binding: NotificationScheduleDialogBinding

    private val scheduleNotification: ScheduleNotificationViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        binding = NotificationScheduleDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        createNotificationChannel()
        fillTextFields()
        binding.submitButton.setOnClickListener { scheduleNotification() }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

//        Toast.makeText(applicationContext, note.id.toString(), Toast.LENGTH_SHORT).show()

        return dialog
    }

    private fun fillTextFields() {
        binding.titleET.setText(note.title)
        binding.messageET.setText(note.description)
    }

    private fun scheduleNotification() {
        val intent = Intent(context, Notifications::class.java)
        val title = binding.titleET.text.toString()
        val message = binding.messageET.text.toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)
        intent.putExtra(iconExtra, note.icon)
        intent.putExtra(idExtra, note.id)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(applicationContext, AlarmManager::class.java)
        val time = getTime()
        alarmManager!!.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time, title, message)
    }

    private fun showAlert(time: Long, title: String, message: String) {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)
        val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")


        scheduleNotification.recordSchedule(
            LogEntity(
                noteId = note.id,
                notification_date = sdf.format(time),
                action_type = NoteActionType.SCHEDULE_NOTIFICATION,
                action_desc = NoteActionType.SCHEDULE_NOTIFICATION.getDescription(note.title),
                action_date = getCurrentDate()
            )
        )

//        AlertDialog.Builder(applicationContext!!)
//            .setTitle("Notification Scheduled")
//            .setMessage(
//                "Title: " + title +
//                        "\nMessage: " + message +
//                        "\nAt: " + dateFormat.format(date) + " " + timeFormat.format(date))
//            .setPositiveButton("Okay"){_,_ ->}
//            .show()
        this.dismiss()
        Toast.makeText(applicationContext, "Notification Schedule", Toast.LENGTH_SHORT).show()
    }

    private fun getTime(): Long {
        val minute = binding.timePicker.minute
        val hour = binding.timePicker.hour
        val day = binding.datePicker.dayOfMonth
        val month = binding.datePicker.month
        val year = binding.datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }

    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager =
            getSystemService(applicationContext!!, NotificationManager::class.java)
        notificationManager!!.createNotificationChannel(channel)
    }
}