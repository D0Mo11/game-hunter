package com.dragic.gamehunter.di

import app.cash.sqldelight.db.SqlDriver
import com.dragic.gamehunter.GameHunterDatabase
import com.dragic.gamehunter.networking.CheapSharkApi
import com.dragic.gamehunter.repository.DealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(cheapSharkApi: CheapSharkApi, driver: SqlDriver, @IoDispatcher ioDispatcher: CoroutineContext): DealRepository =
        DealRepository(cheapSharkApi, GameHunterDatabase(driver), ioDispatcher)
}
