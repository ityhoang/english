package english.com.ui.more

import androidx.fragment.app.viewModels
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishFragmentMoreBinding

class MoreFragment : BaseFragment<EnglishFragmentMoreBinding, MoreViewModel>() {
    override val viewModel: MoreViewModel by viewModels()
    override val layoutId = R.layout.english_fragment_more
}