package english.com.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import english.com.base.BaseViewModel
import english.com.data.model.User
import english.com.data.repository.UserRepository
import english.com.data.session.Session
import english.com.utils.extensions.networkOriginCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository, private val session: Session
) : BaseViewModel() {
    fun login(email: String, password: String, listener: () -> Unit) {
        session.user = User(
            1, listOf(), "", "tyhoang", "ty", "hoang", 0, "tyhoang@gmail.com", "090902931"
        )
        viewModelScope.launch(Dispatchers.Main) {
            listener.invoke()
        }
//        networkOriginCall(block = {
//            userRepository.postLogin(mapOf("username" to email, "password" to password))
//        }, error = null, loading = null, onSuccess = {
//            session.user = it.result
//            listener.invoke()
//        }, onError = {
//            Log.d("tyhoang", "login: ${it.message}")
//        })
    }
}