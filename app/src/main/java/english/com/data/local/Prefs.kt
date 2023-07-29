package english.com.data.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

interface Prefs {
    fun <T> putObject(key: String, obj: T?)

    fun <T> getObject(key: String, type: Class<T>?): T?

    fun clear()
}

class PrefsImpl(context: Context, val gson: Gson) : Prefs {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("English.Prefs", Context.MODE_PRIVATE)
    }

    override fun <T> putObject(key: String, obj: T?) {
        try {
            obj?.let {
                prefs.edit().putString(key, gson.toJson(obj)).apply()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun <T> getObject(key: String, type: Class<T>?): T? {
        return try {
            gson.fromJson(prefs.getString(key, null), type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }
}