package com.example.room.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.room.model.User
import com.example.room.repository.UserRepository

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    suspend fun loadUsers(forceRefresh: Boolean = false) {
        val data = userRepository.getUsers(forceRefresh)
        _users.postValue(data)
    }
}