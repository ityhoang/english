package english.com.base

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import english.com.utils.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

//abstract class BaseViewModel : ViewModel() {
//    val error = SingleLiveEvent<String>()
//    private var _commonError = MutableLiveData<Exception>()
//    val commonError: LiveData<Exception> get() = _commonError
//
//    protected val _loading = MutableLiveData(false)
//    val loading: LiveData<Boolean> = _loading
//
//    fun dispatchStateLoading(loading: Boolean) {
//        _loading.value = false
//    }
//    @MainThread
//    fun Fragment.navigateUp() {
//    }
//}
abstract class BaseViewModel : ViewModel() {
    private val _loading by lazy { MutableLiveData<Boolean>() }
    private val _commonError by lazy { SingleLiveEvent<Exception>() }
    private val _navigation = MutableLiveData<Event<NavigationCommand>>()

    val loading: LiveData<Boolean> get() = _loading
    val commonError: LiveData<Exception> get() = _commonError
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    @MainThread
    fun navigate(direction: NavDirections) {
        _navigation.value = Event(NavigationCommand.To(direction))
    }

    @MainThread
    fun navigateUp() {
        _navigation.value = Event(NavigationCommand.Back)
    }

    fun <T> doAsync(work: suspend () -> T): Job {
        return viewModelScope.launch {
            try {
                work()
            } catch (e: Exception) {
                _commonError.setValue(e)
            }
        }
    }

    fun dispatchStateLoading(loading: Boolean) {
        _loading.value = false
    }

    fun notifyData(liveData: MutableLiveData<*>?) {
        liveData?.value = liveData?.value
    }
}

class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

sealed class NavigationCommand {
    data class To(val directions: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
}