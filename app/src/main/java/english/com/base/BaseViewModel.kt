package english.com.base

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import english.com.utils.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {
    val error = SingleLiveEvent<String>()
    private var _commonError = MutableLiveData<Exception>()
    val commonError: LiveData<Exception> get() = _commonError

    protected val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun dispatchStateLoading(loading: Boolean) {
        _loading.value = false
    }
    @MainThread
    fun Fragment.navigateUp() {
    }
}
