package com.example.baseproject.data.api.post

import com.example.baseproject.data.entity.PostEntity
import com.example.baseproject.data.retrofit.ServiceGenerator
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostApiImpl @Inject constructor() : PostApi {
    private var service = ServiceGenerator.createService(PostService::class.java)
    override fun getListPost(): Call<List<PostEntity>> {
        return service.getListPost()
    }
}