package english.com.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import english.com.R
import english.com.base.BaseActivity
import english.com.databinding.EnglishActivitySplashBinding
import english.com.ui.EnglishMainActivity
import english.com.utils.extensions.runDelay
import english.com.utils.extensions.setFullScreen

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<EnglishActivitySplashBinding>() {

    override val layoutId = R.layout.english_activity_splash
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        runDelay(1000) {
            EnglishMainActivity.start(this@SplashActivity)
            finish()
        }
    }
}