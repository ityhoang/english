package english.com.data.remote

import english.com.data.model.EnglishBaseResponse
import english.com.data.model.User
import retrofit2.http.Body
import retrofit2.http.POST

interface HomeApi {
    @POST("/api/v1/auth/mobile/sign-in")
    suspend fun postLogin(@Body map: Map<String, String>): EnglishBaseResponse<User>
}