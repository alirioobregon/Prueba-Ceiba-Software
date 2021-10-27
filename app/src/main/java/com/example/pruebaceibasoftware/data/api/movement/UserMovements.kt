package com.example.pruebaceibasoftware.data.api.movement

import com.example.pruebaceibasoftware.database.schemas.Posts
import com.example.pruebaceibasoftware.database.schemas.Users

interface UserMovements {
    fun getUserSuccess(data: ArrayList<Users>)

    fun errorGetUsers(error: String)

    fun getPostsSuccess(data: ArrayList<Posts>)

    fun errorGetPosts(error: String)
}