package com.example.baseproject.di

import android.app.Application
import com.example.baseproject.data.api.ApiModule
import com.example.baseproject.data.repository.RepositoryModule
import com.example.baseproject.di.activity.ActivityModule
import com.example.baseproject.di.viewmodel.ViewModelModule
import com.example.baseproject.presentation.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        ApiModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}