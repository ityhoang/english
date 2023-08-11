package english.com.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import english.com.base.BaseViewModel
import english.com.data.local.Prefs
import english.com.data.model.User
import english.com.data.session.Session
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val prefs: Prefs) : BaseViewModel() {
    fun logout() {
        prefs.clear()
    }

    var user: User = prefs.getObject(Session.PREF_USER, User::class.java)!!

}