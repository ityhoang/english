package english.com.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson

interface Prefs {
    fun <T> putObject(key: String, obj: T?)

    fun <T> getObject(key: String, type: Class<T>?): T?

    fun clear()

    fun getFirebaseMessagingToken(): String

    fun setFirebaseMessagingToken(token: String)

    fun getToken(): String
}

class PrefsImpl(context: Context, val gson: Gson) : Prefs {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
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

    override fun getFirebaseMessagingToken(): String {
        return prefs.getString(KEY_FCM_TOKEN, "") ?: ""
    }

    override fun setFirebaseMessagingToken(token: String) {
        prefs.edit {
            putString(KEY_FCM_TOKEN, token)
        }
    }

    override fun getToken() = prefs.getString(KEY_USER_TOKEN, "") ?: ""

    companion object {
        private const val KEY_FCM_TOKEN = "firebase_cloud_messaging_token"
        private const val KEY_USER_TOKEN = "user_token"
        private const val KEY_PREFERENCES = "English.Prefs"
    }
}