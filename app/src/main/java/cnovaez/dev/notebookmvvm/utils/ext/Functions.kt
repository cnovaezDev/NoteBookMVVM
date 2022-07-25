package cnovaez.dev.notebookmvvm.utils.ext

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.time.Instant

fun Activity.color(@ColorRes color: Int) = ContextCompat.getColor(this, color)

fun Activity.image(image: Int) = ContextCompat.getDrawable(this, image)

fun View.color(@ColorRes color: Int) = ContextCompat.getColor(this.context, color)

fun Exception.errorMsg() {
    return this.message.let { message ->
        if (message != null) {
            Log.e("Error: ", message.toString())
        } else {
            Log.e("Error: ", "Unidentified error")
        }
    }
}

fun Fragment.toastMsg(msg: String,context: Context =this.requireActivity(), duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, msg, duration).show()

fun View.toastMsg(msg: String,context: Context =this.context, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, msg, duration).show()

//fun Toast.toastMsg(context: Context,msg: String, duration: Int = Toast.LENGTH_SHORT) =
//    Toast.makeText(context, msg, duration).show()
fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("dd/MM/yy hh:mm a")
    val netDate = Instant.now().toEpochMilli()
    return sdf.format(netDate)
}