package english.com.ui.home

import dagger.hilt.android.lifecycle.HiltViewModel
import english.com.base.BaseViewModel
import english.com.data.model.User
import english.com.data.repository.HomeRepository
import english.com.data.session.Session
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val session: Session, private val homeRepository: HomeRepository) : BaseViewModel() {
    var user: User = session.user!!
}