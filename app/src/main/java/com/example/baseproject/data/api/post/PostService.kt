package com.example.baseproject.data.api.post

import com.example.baseproject.data.entity.PostEntity
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getListPost(): Call<List<PostEntity>>
}