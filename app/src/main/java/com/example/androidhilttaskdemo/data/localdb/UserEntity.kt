package com.example.androidhilttaskdemo.data.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidhilttaskdemo.data.constant.Constant.USER_TABLE

// Entity Class of Room Database
@Entity(tableName = USER_TABLE)
data class UserEntity(
    @PrimaryKey
    val username: String,
    val password: String
)
