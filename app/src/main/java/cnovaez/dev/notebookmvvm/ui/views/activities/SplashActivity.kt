package cnovaez.dev.notebookmvvm.ui.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cnovaez.dev.notebookmvvm.utils.misc.SessionValues

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.extras != null) {
            val noteId = intent.extras!!.getInt("openDetails", -1)
            SessionValues.noteId = noteId;
        }
        startActivity(Intent(this, MainActivity::class.java))
    }
}