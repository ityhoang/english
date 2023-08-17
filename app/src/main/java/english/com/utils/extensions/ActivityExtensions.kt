package english.com.utils.extensions

import android.app.Activity
import androidx.annotation.DimenRes

fun Activity.toDp(@DimenRes dimen: Int) = resources.getDimensionPixelSize(dimen)
