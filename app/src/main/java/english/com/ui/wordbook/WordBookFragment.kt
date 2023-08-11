package english.com.ui.wordbook

import androidx.fragment.app.viewModels
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishWordbookFragmentBinding

class WordBookFragment : BaseFragment<EnglishWordbookFragmentBinding, WordBookViewModel>() {
    override val viewModel: WordBookViewModel by viewModels()
    override val layoutId = R.layout.english_wordbook_fragment
}