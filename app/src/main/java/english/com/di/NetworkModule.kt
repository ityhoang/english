package english.com.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import english.com.BuildConfig
import english.com.core.ServiceBuilder
import english.com.data.local.Prefs
import english.com.data.local.PrefsImpl
import english.com.data.remote.EnglishInterceptor
import english.com.data.remote.UserApi
import okhttp3.Interceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context, gson: Gson): Prefs {
        return PrefsImpl(context, gson)
    }

    @Provides
    @Singleton
    fun provideInterceptor(@ApplicationContext context: Context): Interceptor {
        return EnglishInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideChuckerCollector(@ApplicationContext context: Context) = ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context,
        collector: ChuckerCollector
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(collector)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserAPI(
        @ApplicationContext context: Context,
        gson: Gson,
        prefs: Prefs,
        interceptor: Interceptor,
        chuckerInterceptor: ChuckerInterceptor,
    ): UserApi {
        return createServiceBuilder(
            context = context,
            clazz = UserApi::class.java,
            gson = gson,
            prefs = prefs,
            interceptor = interceptor,
            chuckerInterceptor = chuckerInterceptor
        ).build()
    }

}

fun <T> createServiceBuilder(
    context: Context,
    clazz: Class<T>,
    baseUrl: String = BuildConfig.API_URL,
    gson: Gson,
    prefs: Prefs,
    interceptor: Interceptor,
    chuckerInterceptor: ChuckerInterceptor? = null
): ServiceBuilder<T> {
    return ServiceBuilder(clazz)
        .setBaseUrl(baseUrl)
        .setTokenProvider { prefs.getToken() }
        .isLoggable(BuildConfig.DEBUG)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .setGson(gson)
        .apply {
            chuckerInterceptor?.let {
                addInterceptors(listOf(interceptor, chuckerInterceptor))
            } ?: addInterceptor(interceptor)
        }
}