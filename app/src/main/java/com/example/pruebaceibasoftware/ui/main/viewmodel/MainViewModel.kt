package com.example.pruebaceibasoftware.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebaceibasoftware.data.api.movement.UserMovements
import com.example.pruebaceibasoftware.data.entities.PostVO
import com.example.pruebaceibasoftware.data.entities.UserVO
import com.example.pruebaceibasoftware.data.repository.UserRepository
import com.example.pruebaceibasoftware.database.schemas.Posts
import com.example.pruebaceibasoftware.database.schemas.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(), UserMovements {

    private val repository = UserRepository.instance(this)

    val users: LiveData<ArrayList<Users>> get() = _users
    private val _users = MutableLiveData<ArrayList<Users>>()

    val errorUsers: LiveData<String> get() = _errorUsers
    private val _errorUsers = MutableLiveData<String>()

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUsers()
        }
    }

    override fun getUserSuccess(data: ArrayList<Users>) {
        _users.postValue(data)
    }

    override fun errorGetUsers(error: String) {
        _errorUsers.postValue(error)
    }

    override fun getPostsSuccess(data: ArrayList<Posts>) {

    }

    override fun errorGetPosts(error: String) {

    }

}