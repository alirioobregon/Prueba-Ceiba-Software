package com.example.pruebaceibasoftware.data.repository

import android.util.Log
import com.example.pruebaceibasoftware.data.api.movement.UserMovements
import com.example.pruebaceibasoftware.data.singleton.InstanceSingleton
import com.example.pruebaceibasoftware.database.schemas.Posts
import com.example.pruebaceibasoftware.database.schemas.Users

class UserRepository(private val userMovements: UserMovements) {

    private val remoteData = InstanceSingleton.instanceSingleton?.serviceApi
    private val dataBase = InstanceSingleton.database?.UsersDao()

    companion object {
        fun instance(userMovements: UserMovements) = UserRepository(userMovements)
    }

    suspend fun getUsers() {
        try {

            if (dataBase?.getAllUsers().isNullOrEmpty()) {
                val data = remoteData?.getUsers()
                data?.let { dit ->
                    when (dit.code()) {
                        200 -> {
                            data.body()?.let {
                                dataBase?.addAllUsers(it)
                                userMovements.getUserSuccess(dataBase?.getAllUsers() as ArrayList<Users>)
                            } ?: run {
                                userMovements.errorGetUsers("Data null, status: ${data.code()}")
                            }
                        }
                        else -> {
                            userMovements.errorGetUsers("Error de conexión con el servidor, status: ${data.code()}")
                        }
                    }
                } ?: run {
                    userMovements.errorGetUsers("Data null")
                }
            } else {
                Log.i("user", "users in local")
                userMovements.getUserSuccess(dataBase?.getAllUsers() as ArrayList<Users>)
            }
        } catch (e: Exception) {
            userMovements.errorGetUsers(e.message.toString())
        }
    }


    suspend fun getPostUsers(id: Long) {
        try {
            if (dataBase?.getAllPost().isNullOrEmpty()) {
                val data = remoteData?.getPosts()
                data?.let { dit ->
                    when (dit.code()) {
                        200 -> {
                            data.body()?.let {
                                dataBase?.addAllPosts(it)
                                userMovements.getPostsSuccess(dataBase?.getPostByIdUser(id) as ArrayList<Posts>)
                            } ?: run {
                                userMovements.errorGetPosts("Data null, status: ${data.code()}")
                            }
                        }
                        else -> {
                            userMovements.errorGetPosts("Error de conexión con el servidor, status: ${data.code()}")
                        }
                    }
                } ?: run {
                    userMovements.errorGetPosts("Data null")
                }
            } else {
                userMovements.getPostsSuccess(dataBase?.getPostByIdUser(id) as ArrayList<Posts>)
            }
        } catch (e: Exception) {
            userMovements.errorGetPosts(e.message.toString())
        }
    }

}