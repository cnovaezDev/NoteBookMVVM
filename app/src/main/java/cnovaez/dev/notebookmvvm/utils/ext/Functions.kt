package cnovaez.dev.notebookmvvm.utils

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Activity.color(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun View.color(@ColorRes color: Int) = ContextCompat.getColor(this.context, color)

fun Exception.errorMsg(ex: Exception) {
    return ex.message.let { message ->
        if (message != null) {
            Log.e("Error: ", message.toString())
        } else {
            Log.e("Error: ", "Unidentified error")
        }
    }
}
