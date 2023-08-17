package english.com.ui.account

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishAccountFragmentBinding
import english.com.ui.EnglishMainActivity

@AndroidEntryPoint
class AccountFragment : BaseFragment<EnglishAccountFragmentBinding, AccountViewModel>() {
    override val viewModel: AccountViewModel by viewModels()
    override val layoutId = R.layout.english_account_fragment

    override fun initView() {
        super.initView()
        binding.apply {
            btnLogout.setOnClickListener {
                viewModel.logout()
                EnglishMainActivity.start(requireActivity())
            }
        }
    }
}