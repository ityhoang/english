package english.com.binding

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter


@BindingAdapter("htmlTextValue")
internal fun setHtmlTextValue(
    textView: TextView,
    @StringRes resId: Int
) {
    val htmlText = textView.context.getString(resId)
    textView.text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
}


