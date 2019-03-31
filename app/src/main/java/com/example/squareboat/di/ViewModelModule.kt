package com.example.squareboat.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.squareboat.ui.dashboard.DashBoardViewModel
import com.example.squareboat.ui.search.IconSearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashBoardViewModel::class)
    internal abstract fun dashboardViewModel(viewModel: DashBoardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IconSearchViewModel::class)
    internal abstract fun iconSearchViewModel(viewModel: IconSearchViewModel): ViewModel
    //Add more ViewModels here
}

