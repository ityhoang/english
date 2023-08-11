package english.com.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EnglishBaseResponse<T>(
    @Expose
    @SerializedName("message")
    var message: String?,
    @Expose
    @SerializedName("result")
    var result: T?,
    @Expose
    @SerializedName("error")
    var error: String?,
)