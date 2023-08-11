package english.com.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int = 0,
    val roles: List<String> = listOf(),
    var token: String? = null,
    @SerializedName("username")
    val userName: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var countWords: Long = 0,
    val email: String,
    var phone: String? = null,
) : Parcelable {
    fun getFullName() = "$firstName $lastName"
}
