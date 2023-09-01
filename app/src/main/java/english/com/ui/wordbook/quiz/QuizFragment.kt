package english.com.ui.wordbook.quiz

import androidx.fragment.app.viewModels
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishFragmentQuizBinding

class QuizFragment : BaseFragment<EnglishFragmentQuizBinding, QuizViewModel>() {
    override val viewModel: QuizViewModel by viewModels()
    override val layoutId = R.layout.english_fragment_quiz

    override fun setupHeader() {
        binding.toolbar.setHeader(
            isBack = true, centerTitle = "Quiz",
            onButtonLeft = {
                popBackStack()
            }
        )
    }
}