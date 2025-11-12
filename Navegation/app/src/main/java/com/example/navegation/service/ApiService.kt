package com.example.navegation.service

import com.example.navegation.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("post")
    suspend fun getPosts() : List<Post>
}