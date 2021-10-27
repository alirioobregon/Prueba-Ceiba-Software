package com.example.pruebaceibasoftware.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pruebaceibasoftware.database.schemas.Posts
import com.example.pruebaceibasoftware.database.schemas.Users

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<Users>

    @Insert
    fun addAllUsers(users: List<Users>)

    @Query("SELECT * FROM posts WHERE userId=:id")
    fun getPostByIdUser(id: Long): List<Posts>

    @Query("SELECT * FROM posts")
    fun getAllPost(): List<Posts>

    @Insert
    fun addAllPosts(posts: List<Posts>)
}