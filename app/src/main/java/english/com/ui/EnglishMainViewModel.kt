package english.com.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import english.com.base.BaseViewModel
import english.com.data.repository.UserRepository
import english.com.utils.extensions.runDelay
import javax.inject.Inject

@HiltViewModel
class EnglishMainViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : BaseViewModel() {
    private val _isLogin = MutableLiveData(false)
    val isLogin: LiveData<Boolean> = _isLogin
    fun test() {
//        networkOriginCall(
//            block = {
//                userRepository.postLogin(mapOf("email" to "ducbao", "password" to "abcd1234"))
//            },
//            error = null,
//            loading = null,
//            onSuccess = {
//                Log.d("tyhoang", "test: $it")
//            })
        runDelay(5000) {
            _isLogin.postValue(true)
        }
    }
}