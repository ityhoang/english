package english.com.utils.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import english.com.base.BaseException
import english.com.base.BaseViewModel
import english.com.data.model.EnglishBaseResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.coroutines.cancellation.CancellationException

fun <T> BaseViewModel.networkOriginCall(
    block: suspend () -> T,
    loading: MutableLiveData<Boolean>? = this.loading as MutableLiveData<Boolean>,
    data: MutableLiveData<T>? = null,
    error: MutableLiveData<Exception>? = commonError as MutableLiveData<Exception>,
    onError: ((Exception) -> Unit)? = null,
    onSuccess: ((T) -> Unit)? = null,
    onComplete: (() -> Unit)? = null
) = viewModelScope.launch {
    loading?.postValue(true)
    try {
        val response = runWithMinimumTime(DEFAULT_MINIMUM_TIME_TO_CALL_API) { block() }
//        200 -299
        if (response == null) {
            val baseException = BaseException(BaseException.SERVER, null)
            error?.postValue(baseException)
            onError?.invoke(baseException)
        } else {
            data?.postValue(response)
            onSuccess?.invoke(response)
        }
    } catch (e: Exception) {
        if (e is CancellationException) {
            return@launch
        }
        if (e is HttpException) {
            try {
                val newE = Gson().fromJson(
                    e.response()?.errorBody()?.string(),
                    EnglishBaseResponse
                    ::class.java
                )
                data?.postValue(newE as T)
                onSuccess?.invoke(newE as T)
            } catch (_: Exception) {
                error?.postValue(e)
                onError?.invoke(e)
            }
        } else {
            val baseException = (e as? BaseException) ?: BaseException(e)
            error?.postValue(baseException)
            onError?.invoke(baseException)
            e.printStackTrace()
        }
    } finally {
        loading?.postValue(false)
        onComplete?.invoke()
    }
}