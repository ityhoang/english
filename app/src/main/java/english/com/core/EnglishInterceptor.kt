package english.com.core

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response


/**
 *  Adds the Authorization: header to retrofit HTTP requests and refresh token if it's expired
 */

class EnglishInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
//        builder.addHeader("caId", "33")
//        builder.addHeader("config", "test")
//        builder.addHeader("app-name", "test")
//        builder.addHeader("device-id", context.deviceId())
        return chain.proceed(builder.build())
    }
}
