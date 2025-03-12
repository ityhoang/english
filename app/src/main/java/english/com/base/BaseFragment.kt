package english.com.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import english.com.R
import english.com.databinding.EnglishLayoutHeaderBinding
import english.com.ui.EnglishMainActivity
import english.com.utils.extensions.navOptions
import english.com.utils.extensions.runDelay
import english.com.utils.extensions.setColor
import english.com.utils.extensions.setLightStatusBar

//abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
//    abstract val viewModel: VM
//    lateinit var binding: VB
//    abstract val layoutId: Int
//
//    private var lightStatusBar = true
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        onInitializeData()
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View? {
//        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, null, false)
//        onObserve()
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        WindowInsetsControllerCompat(requireActivity().window, view).isAppearanceLightStatusBars =
//            lightStatusBar
//        onSubscriber()
//        initView()
//    }
//
//    open fun onObserve() {}
//    open fun initView() {
//        setupHeader()
//    }
//
//    fun EnglishLayoutHeaderBinding.setHeader(
//        isBack: Boolean,
//        centerTitle: String? = null,
//        rightTitle: String? = null,
//        subTitle: String? = null,
//        onButtonLeft: (() -> Unit)? = null
//    ) {
//        // button right
//        imvLeft.setImageResource(
//            when (isBack) {
//                true -> R.drawable.english_ic_back
//                false -> R.drawable.english_ic_home
//            }
//        )
//        layoutLeft.setOnClickListener {
//            onButtonLeft?.invoke()
//        }
//
//        //center title
//        tvTitleHeader.isInvisible = centerTitle.isNullOrEmpty()
//        tvTitleHeader.text = centerTitle
//
//        //right title
//        layoutRight.isGone = rightTitle.isNullOrEmpty()
//        subName.isGone = subTitle.isNullOrEmpty()
//        subName.text = subTitle
//        tvRight.isGone = rightTitle.isNullOrEmpty()
//        tvRight.text = rightTitle
//        tvRight.setColor(
//            when (subTitle.isNullOrEmpty()) {
//                true -> R.color.english_font_title
//                false -> R.color.primary_500
//            }, requireContext()
//        )
//    }
//
//    open fun onSubscriber() {}
//    open fun onInitializeData() {}
//    internal open fun setupHeader() {}
//    open fun popBackStack() {
//        findNavController().popBackStack()
//    }
//
//    fun navigate(directions: NavDirections) {
//        findNavController().navigate(directions, navOptions)
//    }
//
//    fun toHome() {
//        (requireActivity() as EnglishMainActivity).binding.bottomNavigation.selectedItemId =
//            R.id.english_home_navigation
//    }
//}


abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    lateinit var binding: VB
    private var lightStatusBar = true
    abstract val viewModel: VM
    abstract val layoutId: Int
//    lateinit var activityLauncher: TrpActivityResult

    private val navController: NavController by lazy {
        findNavController()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        activityLauncher = TrpActivityResult.registerActivityForResult(this)
        onInitializeData()
    }

    override fun onResume() {
        super.onResume()
        runDelay(0L) {
            val activity = requireActivity()
            if (activity is AppCompatActivity) {
                setLightStatusBar(activity, lightStatusBar)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
//        binding.setVariable(BR.viewModel, getViewModel())
        onSubscriber()
        onObserve()
        initBaseEvent()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        super.onViewCreated(view, savedInstanceState)
    }

    protected fun navigate(directions: NavDirections) {
        val currentDestination = navController.currentDestination
        if (currentDestination?.getAction(directions.actionId) != null) {
            navController.navigate(directions, navOptions)
        }
    }

    private fun initBaseEvent() {
        val btnBack = binding.root.findViewWithTag<ImageView>("common.btnBack")
        btnBack?.setOnClickListener {
            onToolbarLeftClicked()
        }

        val btnToolbarRight = binding.root.findViewWithTag<ImageView>("common.btnToolbarRight")
        btnToolbarRight?.setOnClickListener {
            onToolbarRightClicked()
        }
    }

    open fun onToolbarRightClicked() {
        // Handle toolbar right button click here
    }

    open fun onToolbarLeftClicked() {
        popBackStack()
    }

    open fun onInitializeData() {
        // Initialize data here
    }

    open fun onObserve() {
        observeNavigation(viewModel)
    }

    open fun onSubscriber() {
        // Subscribe to ViewModel here
//        getViewModel().commonError.observe(viewLifecycleOwner) { e ->
//            e?.let {
//                if (it is BaseException) {
//                    val msg = it.getMsg()
//                    if (!msg.isNullOrBlank()) {
//                        AndroidComponentExtensionsKt.showAlert(
//                            this@BaseFragments,
//                            null,
//                            it.getMsg(),
//                            null,
//                            0,
//                            null,
//                            false,
//                            false,
//                            0,
//                            null,
//                            null,
//                            1021,
//                            null
//                        )
//                        return@observe
//                    }
//                }
//                AndroidComponentExtensionsKt.showAlert(
//                    this@BaseFragment,
//                    null,
//                    getString(string.trp_some_thing_wrong_here),
//                    null,
//                    0,
//                    null,
//                    false,
//                    false,
//                    0,
//                    null,
//                    null,
//                    1021,
//                    null
//                )
//            }
//        }

    }

    open fun hasPaddingStatusBar(isPadding: Boolean) {
        val context = context
        if (context != null) {
            val statusBarHeight = getStatusBarHeight(context)
            ViewCompat.setPaddingRelative(
                binding.root,
                0,
                if (isPadding) statusBarHeight else 0,
                0,
                0
            )
        }
    }

    fun popBackStack() {
        try {
            if (!navController.popBackStack()) {
                val activity = requireActivity()
                if (activity is FragmentActivity) {
                    activity.onBackPressed()
                }
            }
        } catch (e: Exception) {
            val activity = requireActivity()
            if (activity is FragmentActivity) {
                activity.onBackPressed()
            }
        }
    }

    fun getStatusBarHeight(context: Context): Int {
        val result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            return context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    private fun observeNavigation(viewModel: BaseViewModel) {
        viewModel.navigation.observe(viewLifecycleOwner) { event ->
            event?.getContentIfNotHandled()?.let { command ->
                when (command) {
                    is NavigationCommand.To -> navigate(command.directions)
                    is NavigationCommand.Back -> navController.navigateUp()
                    else -> {}
                }
            }
        }
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

    open fun initView() {
        setupHeader()
    }

    internal open fun setupHeader() {}
    fun toHome() {
        (requireActivity() as EnglishMainActivity).binding.bottomNavigation.selectedItemId =
            R.id.english_home_navigation
    }
}