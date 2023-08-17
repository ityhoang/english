package english.com.ui.account

import dagger.hilt.android.lifecycle.HiltViewModel
import english.com.base.BaseViewModel
import english.com.data.local.Prefs
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val prefs: Prefs) : BaseViewModel() {
    fun logout() {
        prefs.clear()
    }
}