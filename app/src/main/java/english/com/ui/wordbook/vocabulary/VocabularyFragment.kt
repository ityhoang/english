package english.com.ui.wordbook.vocabulary

import androidx.fragment.app.viewModels
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishFragmentVocabularyBinding
import english.com.utils.extensions.hide
import english.com.utils.extensions.show
import english.com.view.DetailDialogFragment

class VocabularyFragment :
    BaseFragment<EnglishFragmentVocabularyBinding, VocabularyViewModel>() {
    override val viewModel: VocabularyViewModel by viewModels()
    override val layoutId = R.layout.english_fragment_vocabulary

    override fun setupHeader() {
        super.setupHeader()
        binding.apply {
            toolbar.imvLeft.setImageResource(R.drawable.english_ic_back)
            toolbar.layoutLeft.onBack()
            toolbar.tvTitleHeader.show()
            toolbar.tvTitleHeader.text = "English"
            toolbar.layoutRight.hide()
        }
    }

    override fun initView() {
        super.initView()
        binding.apply {
            btnQuiz.setOnClickListener {
                DetailDialogFragment().showAllowingStateLoss(childFragmentManager)
            }
        }
    }
}