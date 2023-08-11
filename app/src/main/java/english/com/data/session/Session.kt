package english.com.data.session

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import com.google.gson.Gson
import english.com.data.local.Prefs
import english.com.data.model.User
import javax.inject.Inject

class Session @Inject constructor(context: Context, private val prefs: Prefs) {
    private val _currentUser = MediatorLiveData<User?>()
    val currentUser = _currentUser

    fun setCurrentUser(user: User?) {
        prefs.putObject(PREF_USER, Gson().toJson(user))
        currentUser.value = user
    }

    private fun getCurrentUser(): User? {
        val data = prefs.getObject(PREF_USER, String::class.java)
        data?.let {
            try {
                return Gson().fromJson(it, User::class.java)
            } catch (_: Throwable) {
            }
        }
        return null
    }

    init {
        _currentUser.postValue(getCurrentUser())
    }

    var currentUserId: Int
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
