package com.example.baseproject.data.repository.post

import androidx.lifecycle.LiveData
import com.example.baseproject.data.entity.PostEntity
import com.example.baseproject.data.retrofit.resource.Resource

interface PostRepository {
    fun getListPost(): LiveData<Resource<List<PostEntity>>>
}