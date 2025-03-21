package english.com.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import english.com.R
import english.com.utils.SafeClickListener

fun Activity.setFullScreen() {
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
}

fun View.safeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener { v ->
        onSafeClick(v)
    }
    this.setOnClickListener(safeClickListener)
}

internal fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

internal fun Fragment.hideKeyboard() {
    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusView = requireActivity().currentFocus ?: return
    imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
}

internal fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusView = currentFocus ?: return
    imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
}

internal fun View.show() {
    visibility = View.VISIBLE
}

internal fun View.hide() {
    visibility = View.GONE
}

fun View.toDp(@DimenRes dimen: Int) = resources.getDimensionPixelSize(dimen)


internal fun View.setMarginsInPixels(start: Int = 0, top: Int = 0, end: Int = 0, bottom: Int = 0) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.marginStart = start
        p.topMargin = top
        p.marginEnd = end
        p.bottomMargin = bottom
        this.requestLayout()
    }
}

val navOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.english_slide_in_left)
    .setExitAnim(R.anim.english_slide_out_left)
    .setPopEnterAnim(R.anim.english_slide_in_right)
    .setPopExitAnim(R.anim.english_slide_out_right)
    .build()

fun applyPulsingLoadAnimation(view: View) {
    val animation = AnimationUtils.loadAnimation(view.context, R.anim.english_pulsing)
    view.startAnimation(animation)
}

fun View.startAnimation(viewAnimation: View, isRun: Boolean) {
    if (isRun) {
        show()
        viewAnimation.show()
        applyPulsingLoadAnimation(viewAnimation)
    } else {
        hide()
        viewAnimation.hide()
        clearAnimation(viewAnimation)
    }
}

fun clearAnimation(view: View) {
    view.animation?.cancel()
    view.clearAnimation()
}