package english.com

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.provider.Settings
import androidx.ads.identifier.AdvertisingIdClient
import androidx.appcompat.app.AppCompatDelegate
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
import javax.inject.Inject

@HiltAndroidApp
class EnglishApplication : MultiDexApplication(), LifecycleEventObserver {

    @Inject
    lateinit var session: Session

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.updateContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.updateContext(this)
    }

    override fun onCreate() {
        super.onCreate()
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        if (session.deviceId.isEmpty()) {
            getDeviceId()
        }
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @SuppressLint("HardwareIds")
    @Suppress("BlockingMethodInNonBlockingContext")
    private fun getDeviceId() {
        CoroutineScope(Dispatchers.IO).launch {
            session.deviceId = try {
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