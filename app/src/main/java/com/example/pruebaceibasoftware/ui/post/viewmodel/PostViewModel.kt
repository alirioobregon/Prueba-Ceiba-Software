package com.example.pruebaceibasoftware.ui.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebaceibasoftware.data.api.movement.UserMovements
import com.example.pruebaceibasoftware.data.repository.UserRepository
import com.example.pruebaceibasoftware.database.schemas.Posts
import com.example.pruebaceibasoftware.database.schemas.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel : ViewModel(), UserMovements {

    private val repository = UserRepository.instance(this)

    val posts: LiveData<ArrayList<Posts>> get() = _posts
    private val _posts = MutableLiveData<ArrayList<Posts>>()

    val errorPosts: LiveData<String> get() = _errorPosts
    private val _errorPosts = MutableLiveData<String>()

    fun getPostById(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPostUsers(id)
        }
    }

    override fun getUserSuccess(data: ArrayList<Users>) {

    }

    override fun errorGetUsers(error: String) {

    }

    override fun getPostsSuccess(data: ArrayList<Posts>) {
        _posts.postValue(data)
    }

    override fun errorGetPosts(error: String) {
        _errorPosts.postValue(error)
    }
}