package com.example.pruebaceibasoftware.data.singleton

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebaceibasoftware.R
import com.example.pruebaceibasoftware.data.api.ServiceApi
import com.example.pruebaceibasoftware.database.CeibaDataBase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstanceSingleton(context: Context) {

    @JvmField
    var serviceApi: ServiceApi

    companion object {
        @get:Synchronized
        var instanceSingleton: InstanceSingleton? = null
            private set

        @get:Synchronized
        var database: CeibaDataBase? = null
            private set

        @Synchronized
        fun getInstanceSingleton(context: Context) {
            if (instanceSingleton == null) {
                instanceSingleton = InstanceSingleton(context)
                database = Room.databaseBuilder(context, CeibaDataBase::class.java, "ceiba_bd").build()
            }
        }
    }

    init {

        //creamos el retrofit para los servicios web
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.ip_api))
            .addConverterFactory(GsonConverterFactory.create()).build()
        serviceApi = retrofit.create(ServiceApi::class.java)
    }
}