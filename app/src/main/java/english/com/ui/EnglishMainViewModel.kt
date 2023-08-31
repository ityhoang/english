package english.com.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import english.com.base.BaseViewModel
import english.com.data.repository.UserRepository
import english.com.data.session.Session
import javax.inject.Inject

@HiltViewModel
class EnglishMainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val session: Session
) : BaseViewModel() {
    //    private val _isLogin = MutableLiveData(true)
//    val isLogin: LiveData<Boolean> = _isLogin
    private var _isLogin = session.user != null

    fun loginDone(listener: () -> Unit) {
        if (_isLogin) {
            listener.invoke()
        }
    }
}