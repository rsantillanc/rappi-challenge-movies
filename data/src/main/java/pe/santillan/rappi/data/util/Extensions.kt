package pe.santillan.rappi.data.util

import android.util.Log
import com.google.gson.Gson

inline val <reified T> T.TAG: String
    get() = T::class.java.simpleName

inline fun <reified T> T.log(message: String) = Log.i(TAG, message)
inline fun <reified T> T.logJson(obj: Any) = Log.i(TAG, Gson().toJson(obj))
