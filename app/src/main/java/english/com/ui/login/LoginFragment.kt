package english.com.ui.login

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishFragmentLoginBinding
import english.com.ui.EnglishMainActivity
import english.com.utils.extensions.hideKeyboard
import english.com.utils.extensions.safeOnClickListener

@AndroidEntryPoint
class LoginFragment : BaseFragment<EnglishFragmentLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()
    override val layoutId = R.layout.english_fragment_login
    override fun initView() {
        super.initView()
        binding.apply {
            tvSupport.setOnClickListener {
                navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
            btnSignIn.safeOnClickListener {
                hideKeyboard()
                viewModel.login(
                    email = edtEmail.text.toString(), password = edtPassword.text.toString()
                ) {
                    EnglishMainActivity.start(requireActivity())
                }
            }
        }
    }
}