package english.com.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import english.com.data.local.Prefs
import english.com.data.session.Session
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun getSession(
        @ApplicationContext context: Context,
        prefs: Prefs
    ) : Session = Session(context,prefs)
}
