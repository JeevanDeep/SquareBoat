package com.example.squareboat.di

import com.example.squareboat.App
import com.example.squareboat.ui.dashboard.DashBoardFragment
import com.example.squareboat.ui.search.IconSearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(app: App)
    fun inject(dashBoardFragment: DashBoardFragment)
    fun inject(iconSearchFragment: IconSearchFragment)
}