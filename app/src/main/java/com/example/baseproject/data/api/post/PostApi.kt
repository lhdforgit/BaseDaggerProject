package com.example.baseproject.data.api.post

import com.example.baseproject.data.entity.PostEntity
import retrofit2.Call

interface PostApi {
    fun getListPost(): Call<List<PostEntity>>
}