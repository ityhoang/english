package english.com.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

@SuppressLint("HardwareIds")
fun Context.deviceId(): String {
    return Settings.Secure.getString(contentResolver,
            Settings.Secure.ANDROID_ID)
}