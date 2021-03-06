package com.example.baseproject.data.api

import com.example.baseproject.data.api.post.PostApi
import com.example.baseproject.data.api.post.PostApiImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ApiModule {
    @Singleton
    @Binds
    abstract fun bindPostApi(api: PostApiImpl): PostApi
}