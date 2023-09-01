package english.com.ui.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishFragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<EnglishFragmentHomeBinding, HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()
    override val layoutId = R.layout.english_fragment_home

    override fun setupHeader() {
        binding.toolbar.setHeader(
            isBack = false, subTitle = "Name of myapp",
            rightTitle = viewModel.user.getFullName(),
        )
    }

    override fun initView() {
        super.initView()
        binding.apply {
            tvCountPoint.text = "1"
        }
    }
}