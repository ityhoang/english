package english.com.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import english.com.R
import english.com.base.BaseActivity
import english.com.data.session.Session
import english.com.databinding.EnglishActivitySplashBinding
import english.com.ui.EnglishMainActivity
import english.com.utils.extensions.runDelay
import english.com.utils.extensions.setFullScreen
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<EnglishActivitySplashBinding>() {

    @Inject
    lateinit var session: Session
    override val layoutId = R.layout.english_activity_splash
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        runDelay(1000) {
            EnglishMainActivity.start(this@SplashActivity, isLogin = (session.user != null))
            finish()
        }
    }
}