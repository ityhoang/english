package english.com.utils.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.setColor(id: Int, context: Context) {
    setTextColor(
        ColorStateList.valueOf(
            ContextCompat.getColor(
                context,
                id
            )
        )
    )
}