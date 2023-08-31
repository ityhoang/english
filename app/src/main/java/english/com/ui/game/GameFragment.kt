package english.com.ui.game

import androidx.fragment.app.viewModels
import english.com.R
import english.com.base.BaseFragment
import english.com.databinding.EnglishFragmentGameBinding

class GameFragment : BaseFragment<EnglishFragmentGameBinding, GameViewModel>() {
    override val viewModel: GameViewModel by viewModels()
    override val layoutId = R.layout.english_fragment_game
}