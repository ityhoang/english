package english.com.data.repository

import english.com.data.model.User
import english.com.data.remote.UserApi
import english.com.data.model.EnglishBaseResponse

interface UserRepository {
    suspend fun postLogin(map: Map<String, String>): EnglishBaseResponse<User>
}

class UserRepositoryImp(private val userApi: UserApi) : UserRepository {
    override suspend fun postLogin(map: Map<String, String>) = userApi.postLogin(map)
}

