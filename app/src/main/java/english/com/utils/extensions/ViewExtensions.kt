package english.com.utils.extensions

import android.app.Activity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
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

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusView = currentFocus ?: return
    imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
}