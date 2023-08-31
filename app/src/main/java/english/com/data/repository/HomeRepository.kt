package english.com.data.repository

import english.com.data.model.EnglishBaseResponse
import english.com.data.model.User
import english.com.data.remote.HomeApi

interface HomeRepository {
    suspend fun getWordBook(map: Map<String, String>): EnglishBaseResponse<User>
}

class HomeRepositoryImp(private val homeApi: HomeApi) : HomeRepository {
    override suspend fun getWordBook(map: Map<String, String>) = homeApi.postLogin(map)
}
