package cnovaez.dev.notebookmvvm.utils.misc

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.ui.views.activities.SplashActivity


const val notificationID = 1
const val channelID = "channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"
const val iconExtra = "icon"
const val idExtra = "openDetails"

class Notifications : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val inner_intent: Intent = Intent(context, SplashActivity::class.java)
        inner_intent.putExtra(idExtra, intent.extras!!.getInt(idExtra, -1))
        val pendingIntent =
            PendingIntent.getActivity(context, 0, inner_intent, PendingIntent.FLAG_ONE_SHOT)

        val notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(intent.getIntExtra(iconExtra, R.drawable.clip_ic))
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(messageExtra))
            .addAction(R.drawable.clip_ic, "Open notebook", pendingIntent)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)
    }

}