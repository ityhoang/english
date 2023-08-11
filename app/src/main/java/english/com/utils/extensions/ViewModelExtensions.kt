package english.com.utils.extensions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import english.com.base.BaseException
import english.com.base.BaseViewModel
import english.com.model.EnglishBaseResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.coroutines.cancellation.CancellationException

//fun <T> BaseViewModel.networkCall(
//    block: suspend () -> EnglishBaseResponse<T>,
//    loading: MutableLiveData<Boolean>? = this.loading as MutableLiveData<Boolean>,
//    data: MutableLiveData<T>? = null,
//    error: MutableLiveData<Exception>? = commonError as MutableLiveData<Exception>,
//    onError: ((Exception) -> Unit)? = null,
//    onSuccess: ((EnglishBaseResponse<T>) -> Unit)? = null,
//    onComplete: (() -> Unit)? = null
//) = viewModelScope.launch {
//    this@networkCall.networkOriginCall(block, loading, null, error, onError, { response ->
//        try {
//            if (response.code != HttpURLConnection.HTTP_OK && response.code != 0) {
//                val baseException = BaseException(BaseException.Type.SERVER, response.message)
//                error?.postValue(baseException)
//                onError?.invoke(baseException)
//            } else {
//                data?.postValue(response.data)
//                onSuccess?.invoke(response)
//            }
//        } catch (e: Exception) {
//            if (e is CancellationException) {
//                // do nothing when task cancelled
//                return@networkOriginCall
//            }
//            val baseException = (e as? BaseException) ?: BaseException(e)
//            error?.postValue(baseException)
//            onError?.invoke(baseException)
//            e.printStackTrace()
//        } finally {
//            loading?.postValue(false)
//        }
//    }, onComplete)
//}

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
            val baseException = BaseException(BaseException.Type.SERVER, null)
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