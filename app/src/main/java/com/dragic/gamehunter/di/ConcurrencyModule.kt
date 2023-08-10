package com.dragic.gamehunter.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object ConcurrencyModule {

    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineContext = Dispatchers.IO

    @Provides
    @ComputationDispatcher
    fun provideComputationDispatcher(): CoroutineContext = Dispatchers.Default
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ComputationDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher
