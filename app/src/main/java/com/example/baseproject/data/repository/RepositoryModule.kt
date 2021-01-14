package com.example.baseproject.data.repository

import com.example.baseproject.data.repository.post.PostRepository
import com.example.baseproject.data.repository.post.PostRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindPostRepository(repository: PostRepositoryImpl): PostRepository

}