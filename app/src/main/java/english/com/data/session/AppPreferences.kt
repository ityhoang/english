package english.com.data.session

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder

enum class AppPreferences {
    INSTANCE;

    val customGson: Gson = GsonBuilder().apply {
        excludeFieldsWithoutExposeAnnotation()
    }.create()

    private var preferences: SharedPreferences? = null

    var deviceId: String
        get() = preferences?.getString(DEVICE_ID, "") ?: ""
        set(deviceId) {
            val edt = preferences?.edit()
            edt?.putString(DEVICE_ID, deviceId)
            edt?.apply()
        }

    var accessToken: String
        get() = preferences?.getString(ACCESS_TOKEN, "") ?: ""
        set(accessToken) {
            val edt = preferences?.edit()
            edt?.putString(ACCESS_TOKEN, accessToken)
            edt?.apply()
        }

    var userId: String
        get() = preferences?.getString(USER_ID, "") ?: ""
        set(userId) {
            val edt = preferences?.edit()
            edt?.putString(USER_ID, userId)
            edt?.apply()
        }

    fun removeDeviceId() {
        val edt = preferences?.edit()
        edt?.remove(DEVICE_ID)
        edt?.apply()
    }

    fun removeAccessToken() {
        val edt = preferences?.edit()
        edt?.remove(ACCESS_TOKEN)
        edt?.apply()
    }

    fun removeUserId() {
        val edt = preferences?.edit()
        edt?.remove(USER_ID)
        edt?.apply()
    }

    companion object {
        const val DEVICE_ID = "device_id"
        const val ACCESS_TOKEN = "access_token"
        const val USER_ID = "user_id"
        fun load(context: Context?) {
            INSTANCE.preferences = PreferenceManager.getDefaultSharedPreferences(context)
        }
    }
}