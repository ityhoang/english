package english.com.binding

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.AnimRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import english.com.R


@BindingAdapter("gif")
internal fun setImageGifEnglish(imageView: ImageView, @RawRes rawRes: Int?) {
    rawRes ?: return
    Glide.with(imageView.context).load(rawRes).placeholder(null).into(imageView)
}

@BindingAdapter("animateShowHideFade")
fun startFadeAnimate(view: View, show: Boolean?) =
    animateView(
        view,
        show,
        R.anim.english_fade_enter,
        R.anim.english_fade_leave
    )

fun animateView(
    view: View,
    show: Boolean?,
    @AnimRes enterAnimateRes: Int,
    @AnimRes leaveAnimateRes: Int
) {
    show ?: return
    view.animation?.cancel()
    view.clearAnimation()
    if (view.tag == show) return
    val animate =
        AnimationUtils.loadAnimation(view.context, if (show) enterAnimateRes else leaveAnimateRes)
    animate.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) {
            view.visibility = if (show) View.VISIBLE else view.visibility
        }

        override fun onAnimationEnd(p0: Animation?) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }

        override fun onAnimationRepeat(p0: Animation?) {
        }
    })
    view.tag = show
    view.startAnimation(animate)
}

@BindingAdapter("htmlTextValue")
fun setHtmlTextValue(
    textView: TextView,
    @StringRes resId: Int
) {
    val htmlText = textView.context.getString(resId)
    textView.text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
}