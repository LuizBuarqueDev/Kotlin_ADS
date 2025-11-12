package com.example.room.service

import com.example.room.model.User
import retrofit2.http.GET

interface UserService {
    @GET("users")
    fun fetchUsers(): List<User>
}