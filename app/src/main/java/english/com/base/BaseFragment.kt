package english.com.base

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel>() : Fragment() {
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

    open fun onSubscriber() {}
    open fun onInitializeData() {}
    open fun setupHeader() {}
    open fun popBackStack() {
        findNavController().popBackStack()
    }

    fun navigate(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    fun TextView.setColor(id: Int) {
        setTextColor(
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    requireContext(),
                    id
                )
            )
        )

    }

}