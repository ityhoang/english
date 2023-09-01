package english.com.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @Expose
    val id: Int = 0,
    @Expose
    val roles: List<String> = listOf(),
    @Expose
    var token: String? = null,
    @Expose
    @SerializedName("username")
    val userName: String? = null,
    @Expose
    var firstName: String? = null,
    @Expose
    var lastName: String? = null,
    @Expose
    var countWords: Long = 0,
    @Expose
    val email: String,
    @Expose
    var phone: String? = null,
) : Parcelable {
    fun getFullName() = "$firstName $lastName"
}
