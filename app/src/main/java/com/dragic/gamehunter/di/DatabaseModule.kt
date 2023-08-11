package com.dragic.gamehunter.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.dragic.gamehunter.GameHunterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSqlDriver(application: Application): SqlDriver =
        AndroidSqliteDriver(
            schema = GameHunterDatabase.Schema,
            context = application,
            name = "gameHunter.db"
        )
}
