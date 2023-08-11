package english.com.utils.extensions

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun Boolean?.isTrue(): Boolean = this == true
fun Boolean?.isFalse(): Boolean = this == false
fun Boolean?.isFalseOrNull(): Boolean = this == false || this == null
fun LiveData<Boolean>?.runIfTrue(block: () -> Unit): LiveData<Boolean> =
    MutableLiveData(this?.value)

fun runDelay(long: Long, handle: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        handle.invoke()
    }, long)
}

fun tryBlock(func: () -> Unit): Exception? {
    return try {
        func.invoke()
        null
    } catch (e: Exception) {
        e
    }
}