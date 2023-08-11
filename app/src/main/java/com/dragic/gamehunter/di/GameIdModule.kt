package com.dragic.gamehunter.di

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GameId

@Module
@InstallIn(ViewModelComponent::class)
object GameIdModule {

    @Provides
    @GameId
    @ViewModelScoped
    fun provideGameId(savedStateHandle: SavedStateHandle): Int =
        savedStateHandle.get<Int>("deal_id") ?: throw IllegalArgumentException("Provide valid deal_id")
}
