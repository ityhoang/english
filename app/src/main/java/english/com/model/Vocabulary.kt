package english.com.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vocabulary(
    @Expose
    val id: Int,
    @Expose
    val title: String,
    @Expose
    val count: Int,
    val vocabularies: List<WordBook>
) : Parcelable {

}