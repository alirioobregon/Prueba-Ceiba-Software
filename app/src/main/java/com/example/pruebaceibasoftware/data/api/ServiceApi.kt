package com.example.pruebaceibasoftware.data.api

import com.example.pruebaceibasoftware.database.schemas.Posts
import com.example.pruebaceibasoftware.database.schemas.Users
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    @GET("users")
    suspend fun getUsers(): Response<ArrayList<Users>>


    @GET("posts")
    suspend fun getPosts(): Response<ArrayList<Posts>>
}