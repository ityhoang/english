package english.com.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import english.com.utils.Status
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vocabulary(
    @Expose
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val count: Int,
    @Expose
    var status: Status,
    @Expose
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