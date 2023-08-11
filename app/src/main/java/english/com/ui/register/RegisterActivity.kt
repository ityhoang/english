package english.com.ui.register

import android.app.Activity
import android.content.Context
import android.content.Intent
import english.com.R
import english.com.base.BaseActivity
import english.com.databinding.EnglishActivityRegisterBinding
import english.com.ui.login.LoginActivity
import english.com.utils.extensions.setHtmlTextValue

class RegisterActivity : BaseActivity<EnglishActivityRegisterBinding>() {
    override val layoutId = R.layout.english_activity_register

    override fun initView() {
        super.initView()
        binding.apply {
            setHtmlTextValue(tvSupport, R.string.english_tv_sign_up)
            tvSupport.setOnClickListener {
                LoginActivity.start(this@RegisterActivity)
            }

        }

    }

    companion object {
        fun start(context: Context) {
            val fadeInAnim = R.anim.english_slide_in_left
            val fadeOutAnim = R.anim.english_slide_out_right
            context.startActivity(Intent(context, RegisterActivity::class.java))
            (context as Activity).overridePendingTransition(fadeInAnim, fadeOutAnim)
        }
    }

}