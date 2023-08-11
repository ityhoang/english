package english.com.ui.account

import androidx.fragment.app.viewModels
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishAccountFragmentBinding

class AccountFragment : BaseFragment<EnglishAccountFragmentBinding, AccountViewModel>() {
    override val viewModel: AccountViewModel by viewModels()
    override val layoutId = R.layout.english_account_fragment
}