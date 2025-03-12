package english.com.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import english.com.utils.extensions.toDp

class SpaceItemDecoration(
    private val insetsLeft: Int = 0,
    private val insetsTop: Int = 0,
    private val insetsRight: Int = 0,
    private val insetsBottom: Int = 0,
    private val insetsFirst: Int? = null,
    private val insetsLast: Int? = null,
    private val removeLR: Boolean = false,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val context = parent.context
        val position = parent.getChildAdapterPosition(view)
        val lastItemPosition = parent.adapter?.itemCount?.minus(1)

        val iLeft = insetsLeft.toDp(context).toInt()
        val iTop = insetsTop.toDp(context).toInt()
        val iRight = insetsRight.toDp(context).toInt()
        val iBottom = insetsBottom.toDp(context).toInt()
        var iFirst = 0
        insetsFirst?.let {
            iFirst = it.toDp(context).toInt()
        }
        var iLast = 0
        insetsLast?.let {
            iLast = it.toDp(context).toInt()
        }
        if (removeLR) {
            if (position % 2 == 0) {
                outRect.right = iRight
            } else {
                outRect.left = iLeft
            }
        } else {
            when (position) {
                0 -> {
                    outRect.left = iFirst
                    outRect.right = iRight
                }

                lastItemPosition -> {
                    outRect.right = iLast
                    outRect.left = iLeft
                }

                else -> {
                    outRect.left = iLeft
                    outRect.right = iRight
                }
            }
        }

        outRect.top = iTop
        outRect.bottom = iBottom
    }
}