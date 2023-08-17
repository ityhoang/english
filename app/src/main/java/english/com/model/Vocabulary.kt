package english.com.model

import android.os.Parcelable
import english.com.utils.Status
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vocabulary(
    val id: Int,
    val name: String,
    val count: Int,
    var status: Status,
    val image: String,
) : Parcelable {
    fun getTitle() = when (status) {
        Status.COMPLETED -> "Completed"
        else -> "In Progress"
    }

    fun isColor() = when (status) {
        Status.COMPLETED -> true
        else -> false
    }
}