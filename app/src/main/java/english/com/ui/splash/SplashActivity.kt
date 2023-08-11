package english.com.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import english.com.R
import english.com.base.BaseActivity
import english.com.databinding.EnglishActivitySplashBinding
import english.com.ui.login.LoginActivity
import english.com.utils.extensions.runDelay
import english.com.utils.extensions.setFullScreen

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<EnglishActivitySplashBinding>() {
    override val layoutId = R.layout.english_activity_splash
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        runDelay(1000) {
            LoginActivity.startZoom(this@SplashActivity)
            finish()
        }
    }
}