package english.com.ui.wordbook.vocabulary

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishFragmentVocabularyBinding
import english.com.ui.wordbook.WordBookFragmentDirections
import english.com.ui.wordbook.vocabulary.adapter.VocabularyAdapter
import english.com.utils.DataLocal
import english.com.utils.extensions.baseLinearLayoutManager

class VocabularyFragment :
    BaseFragment<EnglishFragmentVocabularyBinding, VocabularyViewModel>() {
    override val viewModel: VocabularyViewModel by viewModels()
    override val layoutId = R.layout.english_fragment_vocabulary

    private val vocabularyAdapter by lazy {
        VocabularyAdapter(childFragmentManager) {
            navigate(WordBookFragmentDirections.actionWordBookFragmentToVocabularyFragment())
        }
    }


    override fun setupHeader() {
        binding.toolbar.setHeader(
            isBack = true, centerTitle = "Vocabulary",
            onButtonLeft = {
                popBackStack()
            }
        )
    }

    override fun initView() {
        super.initView()
        binding.apply {
            btnQuiz.setOnClickListener {
                navigate(VocabularyFragmentDirections.actionVocabularyFragmentToQuizFragment())
            }
        }

        vocabularyAdapter.submitList(DataLocal.listVocabulary)
        binding.rcVocabulary.apply {
            layoutManager = baseLinearLayoutManager(requireContext(), RecyclerView.VERTICAL)
            adapter = vocabularyAdapter
            itemAnimator = null
        }
    }
}