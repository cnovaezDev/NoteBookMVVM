package cnovaez.dev.notebookmvvm.utils.ext

import android.os.Build
import android.text.Html

fun strong(text: String) = "<strong>$text</strong>"

fun h1(text: String) = "<h1>$text</h1>"

fun h2(text: String) = "<h2>$text</h2>"

fun h3(text: String) = "<h3>$text</h3>"

fun h4(text: String) = "<h4>$text</h4>"

fun h5(text: String) = "<h5>$text</h5>"

fun toHtlmString(text: String) = run {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, 0)
    } else {
        Html.fromHtml(text)
    }
}

