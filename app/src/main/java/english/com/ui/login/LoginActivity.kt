package english.com.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import english.com.R
import english.com.base.BaseActivity
import english.com.databinding.EnglishActivityLoginBinding
import english.com.ui.EnglishMainActivity
import english.com.ui.register.RegisterActivity
import english.com.utils.extensions.hideKeyboard
import english.com.utils.extensions.safeOnClickListener
import english.com.utils.extensions.setHtmlTextValue

@AndroidEntryPoint
class LoginActivity : BaseActivity<EnglishActivityLoginBinding>() {
    override val layoutId = R.layout.english_activity_login
    private val viewModel: LoginViewModel by viewModels()
    override fun initView() {
        super.initView()
        binding.apply {
            setHtmlTextValue(tvSupport, R.string.english_tv_sign_up)
            tvSupport.setOnClickListener {
                RegisterActivity.start(this@LoginActivity)
            }
            btnSignIn.safeOnClickListener  {
                hideKeyboard()
                viewModel.login(
                    email = edtEmail.text.toString(), password = edtPassword.text.toString()
                ) {
                    EnglishMainActivity.start(this@LoginActivity)
                }
            }
        }
    }

    override fun onObserve() {
        super.onObserve()
        if (viewModel.isLogin()) {
            EnglishMainActivity.start(this)
        }
    }

    companion object {
        fun start(context: Context) {
            val fadeInAnim = R.anim.english_slide_in_right
            val fadeOutAnim = R.anim.english_slide_out_left

            context.startActivity(Intent(context, LoginActivity::class.java))
            (context as Activity).overridePendingTransition(fadeInAnim, fadeOutAnim)

        }
        fun startZoom(context: Context) {
            val fadeInAnim = android.R.anim.fade_in
            val fadeOutAnim = android.R.anim.fade_out

            context.startActivity(Intent(context, LoginActivity::class.java))
            (context as Activity).overridePendingTransition(fadeInAnim, fadeOutAnim)

        }
    }
}