package english.com.data.session

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MediatorLiveData
import com.google.gson.Gson
import english.com.data.model.User
import javax.inject.Inject

class Session @Inject constructor(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("English.Prefs", Context.MODE_PRIVATE)
    private val _currentUser = MediatorLiveData<User?>()
    val currentUser = _currentUser

    fun setCurrentUser(user: User?) {
        sharedPreferences.edit().putString(PREF_USER, Gson().toJson(user)).apply()
        currentUser.value = user
    }

    private fun getCurrentUser(): User? {
        val data = sharedPreferences.getString(PREF_USER, null)
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
        get() = sharedPreferences.getInt(PREF_USER_ID, 0)
        set(value) = sharedPreferences.edit().putInt(PREF_USER_ID, value).apply()

    var accessToken: String?
        get() = sharedPreferences.getString(PREF_TOKEN, null)
        set(value) = sharedPreferences.edit().putString(PREF_TOKEN, value).apply()

    var deviceId: String
        get() = sharedPreferences.getString(PREF_DEVICE_ID, null) ?: ""
        set(value) = sharedPreferences.edit().putString(PREF_DEVICE_ID, value).apply()

    companion object {
        const val PREF_USER_ID = "store_id"
        const val PREF_USER = "store_id"
        const val PREF_TOKEN = "token"
        const val PREF_DEVICE_ID = "device_id"
    }
}
