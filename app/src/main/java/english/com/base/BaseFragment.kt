package english.com.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import english.com.R
import english.com.databinding.EnglishLayoutHeaderBinding
import english.com.ui.EnglishMainActivity
import english.com.utils.extensions.navOptions
import english.com.utils.extensions.setColor

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    abstract val viewModel: VM
    lateinit var binding: VB
    abstract val layoutId: Int

    private var lightStatusBar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        onInitializeData()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)
        onObserve()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        WindowInsetsControllerCompat(requireActivity().window, view).isAppearanceLightStatusBars =
            lightStatusBar
        onSubscriber()
        initView()
    }

    open fun onObserve() {}
    open fun initView() {
        setupHeader()
    }

    fun EnglishLayoutHeaderBinding.setHeader(
        isBack: Boolean,
        centerTitle: String? = null,
        rightTitle: String? = null,
        subTitle: String? = null,
        onButtonLeft: (() -> Unit)? = null
    ) {
        // button right
        imvLeft.setImageResource(
            when (isBack) {
                true -> R.drawable.english_ic_back
                false -> R.drawable.english_ic_home
            }
        )
        layoutLeft.setOnClickListener {
            onButtonLeft?.invoke()
        }

        //center title
        tvTitleHeader.isInvisible = centerTitle.isNullOrEmpty()
        tvTitleHeader.text = centerTitle

        //right title
        layoutRight.isGone = rightTitle.isNullOrEmpty()
        subName.isGone = subTitle.isNullOrEmpty()
        subName.text = subTitle
        tvRight.isGone = rightTitle.isNullOrEmpty()
        tvRight.text = rightTitle
        tvRight.setColor(
            when (subTitle.isNullOrEmpty()) {
                true -> R.color.english_font_title
                false -> R.color.primary_500
            }, requireContext()
        )
    }

    open fun onSubscriber() {}
    open fun onInitializeData() {}
    internal open fun setupHeader() {}
    open fun popBackStack() {
        findNavController().popBackStack()
    }

    fun navigate(directions: NavDirections) {
        findNavController().navigate(directions, navOptions)
    }

    fun toHome() {
        (requireActivity() as EnglishMainActivity).binding.bottomNavigation.selectedItemId =
            R.id.english_home_navigation
    }
}