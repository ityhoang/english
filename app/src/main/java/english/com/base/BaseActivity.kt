package english.com.base

import android.content.Context
import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.textfield.TextInputEditText
import english.com.utils.LocaleManager
import english.com.utils.extensions.hideKeyboard

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

    open fun onObserve() {}

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.updateContext(this)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText || v is AppCompatEditText || v is TextInputEditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    hideKeyboard()
                    v.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}