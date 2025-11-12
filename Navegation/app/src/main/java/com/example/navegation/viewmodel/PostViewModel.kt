package com.example.navegation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navegation.model.Post
import com.example.navegation.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository ) : ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val post : LiveData<List<Post>> = _posts

    fun loadPosts(){
        viewModelScope.launch {
            try {
                _posts.value = repository.fetchPosts()
            } catch (e: Exception){

            }
        }
    }
}