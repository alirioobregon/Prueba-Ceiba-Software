package com.example.pruebaceibasoftware.database.schemas

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
class Posts(
    @PrimaryKey var id: Long,
    @ColumnInfo(name = "userId") var userId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String
) : Parcelable {

}