package english.com.data.session

import android.content.Context
import english.com.data.local.Prefs
import english.com.data.model.User
import javax.inject.Inject

class Session @Inject constructor(context: Context, private val prefs: Prefs) {

    var user: User?
        get() = prefs.getObject(PREF_USER, User::class.java)
        set(value) = prefs.putObject(PREF_USER, value)

    var userId: Int
        get() = prefs.getObject(PREF_USER_ID, Int::class.java) ?: 0
        set(value) = prefs.putObject(PREF_USER_ID, value)

    var accessToken: String?
        get() = prefs.getObject(PREF_TOKEN, String::class.java)
        set(value) = prefs.putObject(PREF_TOKEN, value)

    var deviceId: String
        get() = prefs.getObject(PREF_DEVICE_ID, String::class.java) ?: ""
        set(value) = prefs.putObject(PREF_DEVICE_ID, value)

    companion object {
        const val PREF_USER_ID = "user_id"
        const val PREF_USER = "user"
        const val PREF_TOKEN = "token"
        const val PREF_DEVICE_ID = "device_id"
    }
}
