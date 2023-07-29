package english.com.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class User(
    var id: Int = 0,
    var name: String = "",
    var email: String? = null,
    var status: String = ""
):Parcelable
