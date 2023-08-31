package english.com.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import english.com.R
import english.com.base.BaseActivity
import english.com.databinding.EnglishActivityMainBinding
import english.com.utils.extensions.*
import english.com.view.motion.AnimationListener

@AndroidEntryPoint
class EnglishMainActivity : BaseActivity<EnglishActivityMainBinding>() {
    override val layoutId = R.layout.english_activity_main
    private val viewModel: EnglishMainViewModel by viewModels()
    private var currentNavController: LiveData<NavController>? = null
    private var navController: NavController? = null
    private val showBottomBarWithFragments = listOf(
        R.id.homeFragment,
        R.id.wordBookFragment,
        R.id.accountFragment,
        R.id.gameFragment,
    )
    var navGraphIds = arrayListOf(
        R.navigation.english_user_navigation,
        R.navigation.english_home_navigation,
        R.navigation.english_wordbook_navigation,
        R.navigation.english_game_navigation,
        R.navigation.english_account_navigation,
    )

    override fun onObserve() {
        super.onObserve()
        initNavigation()
    }

    private fun initNavigation() {
        binding.bottomNavigation.background = null

        val controller = binding.bottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainer,
            intent = intent
        )
        currentNavController = controller
        viewModel.loginDone {
            binding.bottomNavigation.selectedItemId = R.id.english_home_navigation
        }
        currentNavController?.observe(this) { nv ->
            navController?.removeOnDestinationChangedListener(onDestinationChangedListener)
            navController = nv
            navController?.addOnDestinationChangedListener(onDestinationChangedListener)
        }
    }

    private val onDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            showBottomBar(showBottomBarWithFragments.contains(destination.id))
        }

    private fun showBottomBar(show: Boolean) {
        val toolbar = binding.bottomAppBar
        if (show) {
            binding.bottomAppBar.show()
            toolbar.animate().translationY(0f).setDuration(500)
                .setInterpolator(DecelerateInterpolator())
                .setListener(object : AnimationListener() {
                    override fun onComplete() {
                        toolbar.animate().setListener(null)
                        setPaddingBottomBar(binding.fragmentContainer)
                    }
                }).start()
        } else {
            binding.bottomAppBar.hide()
            binding.fragmentContainer.removePaddingBottomBar()
            toolbar.animate().translationY(toolbar.bottom.toFloat()).setDuration(500)
                .setInterpolator(AccelerateInterpolator()).start()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (navController?.navigateUp().isTrue()) return
        super.onBackPressed()
    }

    companion object {
        fun start(context: Context) {
            val fadeInAnim = android.R.anim.fade_in
            val fadeOutAnim = android.R.anim.fade_out

            context.startActivity(Intent(context, EnglishMainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            (context as Activity).overridePendingTransition(fadeInAnim, fadeOutAnim)
        }
    }
}