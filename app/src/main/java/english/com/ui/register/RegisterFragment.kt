package english.com.ui.register

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishFragmentRegisterBinding

class RegisterFragment : BaseFragment<EnglishFragmentRegisterBinding, RegisterViewModel>() {
    override val layoutId = R.layout.english_fragment_register
    override val viewModel: RegisterViewModel by viewModels()
    override fun initView() {
        super.initView()
        binding.apply {
            tvSupport.setOnClickListener {
                popBackStack()
            }
        }
    }
}