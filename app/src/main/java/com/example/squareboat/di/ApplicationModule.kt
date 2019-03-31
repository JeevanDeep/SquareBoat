package com.example.squareboat.di

import android.app.Application
import com.example.squareboat.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(var app: App) {

    @Provides
    @Singleton
    fun provideApp(): Application {
        return app
    }
}