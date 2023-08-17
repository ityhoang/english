package english.com.utils.extensions

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun View.setStroke(
    @DrawableRes id: Int,
    @DimenRes width: Int,
    @ColorRes color: Int,
): GradientDrawable {
    val shapeDrawable = ContextCompat.getDrawable(
        context,
        id
    ) as GradientDrawable
    shapeDrawable.setStroke(
        resources.getDimensionPixelSize(width),
        ContextCompat.getColor(context, color)
    )
    return shapeDrawable
}





