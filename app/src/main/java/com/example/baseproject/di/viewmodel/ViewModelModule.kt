package com.example.baseproject.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.baseproject.MainViewModel
import com.example.baseproject.di.common.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel?
}