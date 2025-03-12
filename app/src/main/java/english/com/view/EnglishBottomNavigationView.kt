package english.com.view

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.doOnPreDraw
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.google.android.material.bottomnavigation.BottomNavigationView
import english.com.R
import kotlin.math.abs

@SuppressLint("CustomViewStyleable")
@Suppress("DEPRECATION", "SameParameterValue")
class EnglishBottomNavigationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : BottomNavigationView(context, attrs), BottomNavigationView.OnNavigationItemSelectedListener {

    private var externalSelectedListener: OnItemSelectedListener? = null
    private var animator: ValueAnimator? = null

    private val indicator = RectF()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color_main)
    }

    private val shadow = RectF()
    private val shadowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }
    var path = Path()

    private var indicatorShadowColor = ContextCompat.getColor(context, R.color.color_bt_grey)
    private var indicatorHeaderColor = ContextCompat.getColor(context, R.color.color_main)
    private var indicatorHeaderHeight = resources.getDimension(R.dimen.dip_2)
    private var indicatorShadowVisible = true

    private var iconMap = HashMap<Int, Pair<Int, Int>>()
    private var defaultSize = 0F

    init {
        super.setOnItemSelectedListener(this)
        attrs?.apply {
            val ta = context.obtainStyledAttributes(this, R.styleable.EnglishBottomNavigationView)
            indicatorHeaderHeight = ta.getDimension(
                R.styleable.EnglishBottomNavigationView_indicatorHeaderHeight, indicatorHeaderHeight
            )
            indicatorHeaderColor = ta.getColor(
                R.styleable.EnglishBottomNavigationView_indicatorHeaderColor, indicatorHeaderColor
            )
            indicatorShadowColor = ta.getColor(
                R.styleable.EnglishBottomNavigationView_indicatorShadowColor, indicatorShadowColor
            )
            indicatorShadowVisible = ta.getBoolean(
                R.styleable.EnglishBottomNavigationView_indicatorShadowVisible,
                indicatorShadowVisible
            )

            ta.recycle()
        }
    }

    override fun getMaxItemCount(): Int {
        return 6
    }

    fun setChangeIcon(list: HashMap<Int, Pair<Int, Int>>) {
        iconMap = list
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        menu.clear()
//        inflateMenu(R.menu.english_home_menu)
        if (externalSelectedListener?.onNavigationItemSelected(item) != false) {
            if (iconMap.isNotEmpty()) {
                val selectedIcon = iconMap[item.itemId]
                for (i in 0 until menu.size()) {
                    val menuItemId = menu.getItem(i).itemId

                    val menuItem = menu.findItem(menuItemId)

                    if (menuItemId == item.itemId) {
                        if (selectedIcon != null) {
                            menuItem?.setIcon(selectedIcon.second)
                        }
                    } else {
                        val unselectedIcon = iconMap[menuItemId]
                        if (unselectedIcon != null && unselectedIcon != selectedIcon) {
                            menuItem?.setIcon(unselectedIcon.first)
                        }
                    }
                }
            }
            val paint = Paint()
            val textSize = resources.getDimensionPixelSize(R.dimen.sp_11)
            paint.textSize = textSize.toFloat()
            val sizeText = paint.measureText(item.title.toString())
            val sizeIcon = item.icon?.intrinsicWidth?.toFloat() ?: 0f
            defaultSize = if (sizeText > sizeIcon) {
                sizeText + resources.getDimension(R.dimen.dip_10)
            } else {
                sizeIcon + resources.getDimension(R.dimen.dip_10)
            }
            onItemSelected(item.itemId)
            return true
        }
        return false
    }

    override fun setOnItemSelectedListener(listener: OnItemSelectedListener?) {
        externalSelectedListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        doOnPreDraw {
            onItemSelected(selectedItemId, false)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        cancelAnimator(setEndValues = true)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (isLaidOut) {
            val cornerRadius = indicator.height() / 2f
            paint.color = indicatorHeaderColor
            canvas.drawRoundRect(indicator, cornerRadius, cornerRadius, paint)
            if (indicatorShadowVisible) {
                canvas.drawPath(path, shadowPaint)
            }
        }
    }

    private fun onItemSelected(itemId: Int, animate: Boolean = true) {
        if (!isLaidOut) return

        cancelAnimator(setEndValues = false)

        val itemView = findViewById<View>(itemId) ?: return
        val fromCenterX = indicator.centerX()
        val fromScale = indicator.width() / defaultSize

        animator = ValueAnimator.ofFloat(MAX_SCALE, fromScale, DEFAULT_SCALE).apply {
            addUpdateListener {
                val progress = it.animatedFraction
                val distanceTravelled = linearInterpolation(progress, fromCenterX, itemView.centerX)

                val scale = it.animatedValue as Float
                val indicatorWidth = defaultSize * scale

                val left = distanceTravelled - indicatorWidth / 2F
                val top = indicator.top
                val right = distanceTravelled + indicatorWidth / 2F
                val bottom = indicator.top + indicatorHeaderHeight

                indicator.set(left, top, right, bottom)

                val leftShadow = (indicator.centerX() - indicator.width() / 2)
                val topShadow = itemView.top + indicatorHeaderHeight
                val rightShadow = (indicator.centerX() + indicator.width() / 2)
                val bottomShadow = itemView.bottom.toFloat()

                shadowPaint.shader = LinearGradient(
                    0f,
                    0f,
                    0f,
                    itemView.height.toFloat(),
                    intArrayOf(getColorWithAlpha(indicatorShadowColor, 50), Color.TRANSPARENT),
                    null,
                    Shader.TileMode.CLAMP
                )

                shadow.set(leftShadow, topShadow, rightShadow, bottomShadow)
                path = Path()
                path.apply {
                    moveTo(
                        shadow.centerX() - shadow.width() / 2,
                        shadow.centerY() - shadow.height() / 2
                    )
                    lineTo(
                        shadow.centerX() + shadow.width() / 2,
                        shadow.centerY() - shadow.height() / 2
                    )
                    lineTo(
                        shadow.centerX() + shadow.width() / 2,
                        shadow.centerY() + shadow.height() / 2
                    )
                    lineTo(
                        shadow.centerX() - shadow.width() / 2,
                        shadow.centerY() + shadow.height() / 2
                    )
                    lineTo(
                        shadow.centerX() - shadow.width() / 2,
                        shadow.centerY() - shadow.height() / 2
                    )
                }

                invalidate()
            }

            interpolator = LinearOutSlowInInterpolator()

            val distanceToMove = abs(fromCenterX - itemView.centerX)
            duration = if (animate) calculateDuration(distanceToMove) else 0L

            start()
        }
    }

    private fun getColorWithAlpha(color: Int, ratio: Int): Int {
        return ColorUtils.setAlphaComponent(color, ((ratio * 255) / 100))
    }

    /**
     * Linear interpolation between 'a' and 'b' based on the progress 't'
     */
    private fun linearInterpolation(t: Float, a: Float, b: Float) = (1 - t) * a + t * b

    /**
     * Calculates a duration for the translation based on a fixed duration + a duration
     * based on the distance the indicator is being moved.
     */
    private fun calculateDuration(distance: Float) =
        (BASE_DURATION + VARIABLE_DURATION * (distance / width).coerceIn(0f, 1f)).toLong()

    /**
     * Convenience property for getting the center X value of a View
     */
    private val View.centerX get() = left + width / 2f

    private fun cancelAnimator(setEndValues: Boolean) = animator?.let {
        if (setEndValues) {
            it.end()
        } else {
            it.cancel()
        }
        it.removeAllUpdateListeners()
        animator = null
    }

    companion object {
        private const val DEFAULT_SCALE = 1f
        private const val MAX_SCALE = 1f
        private const val BASE_DURATION = 300L
        private const val VARIABLE_DURATION = 300L
    }
}