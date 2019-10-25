package eg.foureg.hitme.logger

import android.util.Log

object Logger {
    fun debug(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    fun error(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    fun info(tag: String, msg: String) {
        Log.i(tag, msg)
    }
}