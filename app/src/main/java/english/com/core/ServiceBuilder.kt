package english.com.core

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder<T>(private val serviceClass: Class<T>) {
    private var appId: String = ""
    private var baseUrl: String = ""
    private var callAdapterFactories: ArrayList<CallAdapter.Factory> = ArrayList()
    private var converterFactories: ArrayList<Converter.Factory> = ArrayList()
    private var gson: Gson? = null
    private var interceptors: ArrayList<Interceptor> = ArrayList()
    private var isLoggable: Boolean = false
    private var secretKey: String = ""
    private var timeout: Long = 60
    private var tokenProvider: (() -> String)? = null

    fun addCallAdapterFactories(factories: List<CallAdapter.Factory>): ServiceBuilder<T> {
        callAdapterFactories.addAll(factories)
        return this
    }

    fun addCallAdapterFactory(factory: CallAdapter.Factory): ServiceBuilder<T> {
        callAdapterFactories.add(factory)
        return this
    }

    fun addConverterFactories(factories: List<Converter.Factory>): ServiceBuilder<T> {
        converterFactories.addAll(factories)
        return this
    }

    fun addConverterFactory(factory: Converter.Factory): ServiceBuilder<T> {
        converterFactories.add(factory)
        return this
    }

    fun addInterceptor(interceptor: Interceptor): ServiceBuilder<T> {
        interceptors.add(interceptor)
        return this
    }

    fun addInterceptors(interceptors: List<Interceptor>): ServiceBuilder<T> {
        this.interceptors.addAll(interceptors)
        return this
    }

    fun setAppId(appId: String): ServiceBuilder<T> {
        this.appId = appId
        return this
    }

    fun setBaseUrl(baseUrl: String): ServiceBuilder<T> {
        this.baseUrl = baseUrl
        return this
    }

    fun setGson(gson: Gson): ServiceBuilder<T> {
        this.gson = gson
        return this
    }

    fun setSecretKey(secretKey: String): ServiceBuilder<T> {
        this.secretKey = secretKey
        return this
    }

    fun setTimeOut(timeout: Long): ServiceBuilder<T> {
        this.timeout = timeout
        return this
    }

    fun isLoggable(isLoggable: Boolean): ServiceBuilder<T> {
        this.isLoggable = isLoggable
        return this
    }

    fun setTokenProvider(tokenProvider: () -> String): ServiceBuilder<T> {
        this.tokenProvider = tokenProvider
        return this
    }

    fun build(): T {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .apply {
                for (factory in callAdapterFactories) {
                    addCallAdapterFactory(factory)
                }
                for (factory in converterFactories) {
                    addConverterFactory(factory)
                }
                if (gson != null) {
                    addConverterFactory(GsonConverterFactory.create(gson!!))
                }
            }

        val okHttpClientBuilder = OkHttpClient.Builder()
            .apply {
                for (interceptor in interceptors) {
                    addInterceptor(interceptor)
                }
            }

        if (isLoggable) {
            okHttpClientBuilder.addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
        }

        /***
        if token null call api reset token
        if(tokenProvider == null) tokenProvider = apiResetToke()
        tokenProvider?.let { provider ->
            okHttpClientBuilder.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("Authorization", "Bearer ${provider.invoke()}")
                    .build()
                chain.proceed(request)
            }
        }
         ***/

        tokenProvider?.let { provider ->
            okHttpClientBuilder.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("Authorization", "Bearer ${provider.invoke()}")
                    .build()
                chain.proceed(request)
            }
        }

        val retrofit = retrofitBuilder.client(okHttpClientBuilder.build()).build()
        return retrofit.create(serviceClass)
    }
}