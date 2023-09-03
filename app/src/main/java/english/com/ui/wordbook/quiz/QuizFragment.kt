package english.com.ui.wordbook.quiz

import androidx.fragment.app.viewModels
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishFragmentQuizBinding
import english.com.ui.wordbook.quiz.adapter.QuizAdapter
import english.com.utils.Constants
import english.com.utils.DataLocal
import english.com.utils.SpaceItemDecoration
import english.com.utils.extensions.baseGridLayoutManager

class QuizFragment : BaseFragment<EnglishFragmentQuizBinding, QuizViewModel>() {
    override val viewModel: QuizViewModel by viewModels()
    override val layoutId = R.layout.english_fragment_quiz
    private val quizAdapter by lazy {
        QuizAdapter {}
    }

    override fun setupHeader() {
        binding.toolbar.setHeader(isBack = true, centerTitle = "Quiz", onButtonLeft = {
            popBackStack()
        })
    }

    override fun initView() {
        super.initView()
        quizAdapter.submitList(DataLocal.listQuiz)
        binding.rcWordBook.apply {
            layoutManager = baseGridLayoutManager(requireContext(), 2)
            adapter = quizAdapter
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