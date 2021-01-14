package com.example.baseproject.di.activity

import com.example.baseproject.presentation.main.MainActivity
import com.example.baseproject.di.common.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract fun mainAct() : MainActivity
}