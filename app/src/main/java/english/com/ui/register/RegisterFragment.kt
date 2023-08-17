package english.com.ui.register

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.viewModels
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishFragmentRegisterBinding
import english.com.utils.extensions.setHtmlTextValue

class RegisterFragment : BaseFragment<EnglishFragmentRegisterBinding, RegisterViewModel>() {
    override val layoutId = R.layout.english_fragment_register
    override val viewModel: RegisterViewModel by viewModels()
    override fun initView() {
        super.initView()
        binding.apply {
            setHtmlTextValue(tvSupport, R.string.english_tv_sign_up)
//            tvSupport.setOnClickListener {
//                LoginFragment.start(this@RegisterFragment)
//            }

        }

    }

    companion object {
        fun start(context: Context) {
            val fadeInAnim = R.anim.english_slide_in_left
            val fadeOutAnim = R.anim.english_slide_out_right
            context.startActivity(Intent(context, RegisterFragment::class.java))
            (context as Activity).overridePendingTransition(fadeInAnim, fadeOutAnim)
        }
    }

}