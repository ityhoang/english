package english.com.view.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.widget.AppCompatImageView
import english.com.utils.ScreenUtils

class MoveImageView : AppCompatImageView {
    private var startX = 0
    private var startY = 0
    private var exitTi: Long = 0
    private var widthd = 0
    private var screenHeight = 0
    private var statusHeight = 0
    private var navigationHeight = 0

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    constructor(context: Context?) : super(context!!) {
        init()
    }

    private fun init() {
        widthd = ScreenUtils.getScreenWidth(context)
        screenHeight = ScreenUtils.getScreenHeight(context) //获得屏幕高度
        statusHeight = ScreenUtils.getStatusHeight(context) //获得状态栏的高度
        navigationHeight = ScreenUtils.getVirtualBarHeigh(context) //获取虚拟功能键高度
    }

    fun setWidthHeight(height: Int) {
        screenHeight = height
    }

    interface ClickImageViewCallBack {
        fun onClickSideEnd()
    }

    private var onClickCallBack: ClickImageViewCallBack? = null
    fun setCallBackClick(clickImageViewCallBack: ClickImageViewCallBack?) {
        onClickCallBack = clickImageViewCallBack
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x.toInt()
                startY = event.y.toInt()
                exitTi = System.currentTimeMillis()
                val sideEndX = (getWidth() / 5 * 4).toFloat()
                val sideTopY = (height / 5).toFloat()
                if (event.x > sideEndX && event.y < sideTopY) {
                    if (onClickCallBack != null) {
                        onClickCallBack!!.onClickSideEnd()
                    }
                }
            }

            MotionEvent.ACTION_MOVE -> {
                val loast_x = event.x.toInt()
                val loast_y = event.y.toInt()
                val px = loast_x - startX
                val py = loast_y - startY
                val x = x + px
                var y = y + py


                //x = x < 0 ? 0 : x > width - getWidth() ? width - getWidth() : x;
                if (y < 0) {
                    y = 0f
                }


                if (y > screenHeight - height) {
                    y = (screenHeight - height).toFloat()
                }
                Log.e("position:", "dX:$x dY:$y")
                setX(x)
                setY(y)
            }

            MotionEvent.ACTION_UP -> {
                val rawX = event.rawX.toInt()
                if (rawX >= width / 2) {
                    animate().setInterpolator(DecelerateInterpolator())
                        .setDuration(500)
                        .xBy(width - getWidth() - x)
                        .start()
                } else {
                    val oa = ObjectAnimator.ofFloat(this, "x", x, 0f)
                    oa.interpolator = DecelerateInterpolator()
                    oa.duration = 500
                    oa.start()
                }
                if (System.currentTimeMillis() - exitTi > 200) {
                    return true
                }
            }
        }

        return super.onTouchEvent(event)
    }
}