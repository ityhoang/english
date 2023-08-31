package english.com.ui.home

import android.view.View
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
        binding.apply {
            toolbar.subName.visibility = View.VISIBLE
            toolbar.tvRight.text = viewModel.user.getFullName()
            toolbar.tvRight.setColor(R.color.primary_500)
        }
    }

    override fun initView() {
        super.initView()
        binding.apply {
            tvCountPoint.text = "1"
        }
    }
}