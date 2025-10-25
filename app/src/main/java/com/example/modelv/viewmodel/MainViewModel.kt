package com.example.modelv.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.modelv.model.User

class MainViewModel : ViewModel () {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun loadUser() {
        _user.value = User("Luiz", 21)
    }

    viewModel.user.
}