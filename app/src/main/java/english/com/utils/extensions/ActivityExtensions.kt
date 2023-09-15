package english.com.utils.extensions

import android.app.Activity
import android.util.TypedValue
import androidx.annotation.DimenRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentContainerView

fun Activity.toDp(@DimenRes dimen: Int) = resources.getDimensionPixelSize(dimen)


fun Activity.setPaddingBottomBar(fragmentContainer: FragmentContainerView) {
    val actionBarSizeAttr = android.R.attr.actionBarSize

    val typedValue = TypedValue()
    if (theme.resolveAttribute(actionBarSizeAttr, typedValue, true)) {
        val actionBarSize = TypedValue.complexToDimensionPixelSize(
            typedValue.data, resources.displayMetrics
        )
        fragmentContainer.setPadding(0, 0, 0, actionBarSize)
    }
}

fun setLightStatusBar(activity: AppCompatActivity, isLight: Boolean) {
    WindowInsetsControllerCompat(
        activity.window,
        activity.window.decorView
    ).isAppearanceLightStatusBars =
        isLight
}

fun FragmentContainerView.removePaddingBottomBar() {
    setPadding(0, 0, 0, 0)
}

