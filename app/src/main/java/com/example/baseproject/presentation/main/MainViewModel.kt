package com.example.baseproject.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.baseproject.data.entity.PostEntity
import com.example.baseproject.data.repository.post.PostRepository
import com.example.baseproject.data.retrofit.resource.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    fun getListPost(): LiveData<Resource<List<PostEntity>>> {
        return postRepository.getListPost()
    }

}