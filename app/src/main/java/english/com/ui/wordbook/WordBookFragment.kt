package english.com.ui.wordbook

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishWordbookFragmentBinding
import english.com.ui.wordbook.adapter.WordBookAdapter
import english.com.utils.Constants
import english.com.utils.DataLocal
import english.com.utils.SpaceItemDecoration
import english.com.utils.extensions.baseGridLayoutManager

@AndroidEntryPoint
class WordBookFragment : BaseFragment<EnglishWordbookFragmentBinding, WordBookViewModel>() {
    override val viewModel: WordBookViewModel by viewModels()
    override val layoutId = R.layout.english_wordbook_fragment
    private val wordBookAdapter by lazy {
        WordBookAdapter { _, _ ->
            navigate(WordBookFragmentDirections.actionWordBookFragmentToVocabularyFragment())
        }
    }

    override fun setupHeader() {
        binding.toolbar.setHeader(
            isBack = false, centerTitle = "Word Book",
            onButtonLeft = {
                toHome()
            }
        )
    }

    override fun initView() {
        super.initView()
        wordBookAdapter.submitList(DataLocal.listWordBook)
        binding.rcWordBook.apply {
            layoutManager = baseGridLayoutManager(requireContext(), 2)
            adapter = wordBookAdapter
            addItemDecoration(
                SpaceItemDecoration(
                    insetsLeft = Constants.spaceItem,
                    insetsRight = Constants.spaceItem,
                    insetsBottom = Constants.spaceItem * 2,
                    insetsLast = Constants.spaceItem,
                    insetsFirst = Constants.spaceItem
                )
            )
        }
    }
}