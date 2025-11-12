package com.example.navegation.repository

import com.example.navegation.model.Post
import com.example.navegation.service.RetrofitInstance

class PostRepository {
        private val api = RetrofitInstance.api


    suspend fun  fetchPosts(): List<Post> = api.getPosts()

}