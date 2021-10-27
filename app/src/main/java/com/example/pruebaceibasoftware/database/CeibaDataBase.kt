package com.example.pruebaceibasoftware.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebaceibasoftware.database.dao.UsersDao
import com.example.pruebaceibasoftware.database.schemas.Posts
import com.example.pruebaceibasoftware.database.schemas.Users

@Database(entities = [Users::class, Posts::class], version = 1)
abstract class CeibaDataBase : RoomDatabase() {
    abstract fun UsersDao(): UsersDao
}