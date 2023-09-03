package english.com.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz(
    @Expose
    val id: Int,
    @Expose
    val answer: String,
) : Parcelable {

}