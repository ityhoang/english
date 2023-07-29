package english.com
import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import androidx.ads.identifier.AdvertisingIdClient
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDexApplication

import dagger.hilt.android.HiltAndroidApp
import english.com.data.session.Session
import english.com.utils.LocaleManager
import english.com.utils.eventbus.SecurityEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

@HiltAndroidApp
class EnglishApplication : MultiDexApplication(), LifecycleEventObserver {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.updateContext(base))
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.updateContext(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (Session(this).deviceId.isEmpty()) {
            getDeviceId()
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

    }


    @SuppressLint("HardwareIds")
    @Suppress("BlockingMethodInNonBlockingContext")
    private fun getDeviceId() {
        CoroutineScope(Dispatchers.IO).launch {
            Session(this@EnglishApplication).deviceId = try {
                AdvertisingIdClient.getAdvertisingIdInfo(this@EnglishApplication)
                    .get()?.id?.takeIf { it.isNotEmpty() } ?: Settings.Secure.getString(
                    contentResolver,
                    Settings.Secure.ANDROID_ID
                )

            } catch (e: Throwable) {
                Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
            }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_START) {
            EventBus.getDefault().post(SecurityEvent)
        }
    }
}