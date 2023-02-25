package com.example.mymovieapp.app.di

import android.content.Context
import com.example.domain.helper.DispatchersProvider
import com.example.mymovieapp.app.utils.ResourceProvider
import com.example.mymovieapp.app.utils.communication.NavigationCommunication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNavigationCommunication(): NavigationCommunication = NavigationCommunication.Base()


    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideDispatchers(): DispatchersProvider = DispatchersProvider.Base()

    @Provides
    fun provideResourceProvider(
        @ApplicationContext context: Context,
    ): ResourceProvider = ResourceProvider.Base(context = context)
}