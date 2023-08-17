package english.com.utils.extensions

import android.content.Context
import android.util.TypedValue

fun Number.toPx(context: Context, units: Int = TypedValue.COMPLEX_UNIT_DIP): Float =
    TypedValue.applyDimension(
        units,
        this.toFloat(),
        context.resources.displayMetrics
    )

fun Number.toSp(context: Context, units: Int = TypedValue.COMPLEX_UNIT_SP): Float =
    TypedValue.applyDimension(
        units,
        this.toFloat(),
        context.resources.displayMetrics
    )

fun Number.toDp(context: Context, units: Int = TypedValue.COMPLEX_UNIT_DIP): Float =
    TypedValue.applyDimension(
        units,
        this.toFloat(),
        context.resources.displayMetrics
    )


//internal interface ResourceHelper {
//
//    val res: Resources
//
//    val Number.dp: Float get() = toPx()
//    val Number.sp: Float get() = toPx(units = TypedValue.COMPLEX_UNIT_SP)
//
//    fun Number.toPx(units: Int = TypedValue.COMPLEX_UNIT_DIP): Float = TypedValue.applyDimension(
//        units,
//        this.toFloat(),
//        res.displayMetrics
//    )
//
//}
