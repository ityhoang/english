package english.com.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import english.com.data.remote.HomeApi
import english.com.data.remote.UserApi
import english.com.data.repository.HomeRepository
import english.com.data.repository.HomeRepositoryImp
import english.com.data.repository.UserRepository
import english.com.data.repository.UserRepositoryImp
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi): UserRepository = UserRepositoryImp(api)

    @Provides
    @Singleton
    fun provideHomeRepository(api: HomeApi): HomeRepository = HomeRepositoryImp(api)
}