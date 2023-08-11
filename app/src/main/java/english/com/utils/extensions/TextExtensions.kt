package english.com.utils.extensions

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("htmlTextValue")
fun setHtmlTextValue(
    textView: TextView,
    @StringRes resId: Int
) {
    val htmlText = textView.context.getString(resId)
    textView.text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
}


