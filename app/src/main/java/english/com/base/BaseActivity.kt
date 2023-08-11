package english.com.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import english.com.utils.LocaleManager

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
    open lateinit var binding: VB

    abstract val layoutId: Int

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.updateContext(base))
    }

//    override fun setContentView(layoutResID: Int) {
//        DataBindingUtil.setContentView<VB>(this, layoutId)
//        super.setContentView(layoutResID)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, layoutId)
        setContentView(binding.root)
        initView()
        onObserve()
    }

    open fun initView() {}

    open fun onObserve(){}

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.updateContext(this)
    }
}