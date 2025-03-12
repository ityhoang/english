package english.com.ui.account

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
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
    override fun setupHeader() {
        binding.toolbar.setHeader(
            isBack = false, centerTitle = "Account",
            onButtonLeft = {
                toHome()
            }
        )
    }

    override fun initView() {
        super.initView()
        binding.apply {
            btnLogout.setOnClickListener {
                viewModel.logout()
                EnglishMainActivity.start(requireActivity())
            }
            btnDark.setOnClickListener {
                val nightModeFlags = requireActivity().resources.configuration.uiMode and
                        Configuration.UI_MODE_NIGHT_MASK
                when (nightModeFlags) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }

                    Configuration.UI_MODE_NIGHT_NO -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                }
                requireActivity().recreate()
            }
        }
    }


}