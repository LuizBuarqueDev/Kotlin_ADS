package com.example.room.repository

import com.example.room.dao.UserDAO
import com.example.room.model.User
import com.example.room.service.UserService

class UserRepository (
    private val userDAO : UserDAO,
    private val userService : UserService
) {

    suspend fun getUsers(forceRefresh : Boolean = false): List<User> {
    val localData = userDAO.getAll()
        return localData.ifEmpty {
            val remoteData = userService.fetchUsers()
            userDAO.insertAll(remoteData)
            remoteData
        }
    }
}