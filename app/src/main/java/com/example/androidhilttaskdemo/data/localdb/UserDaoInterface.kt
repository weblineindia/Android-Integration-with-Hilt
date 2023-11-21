package com.example.androidhilttaskdemo.data.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// ROOM Database's DAO interface
@Dao
interface UserDaoInterface {

    // Method to fetch from Database
    @Query("SELECT * FROM userTable WHERE username=:userName")
    fun getUser(userName:String): UserEntity

    // Method to insert in room database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun registerUser(user: UserEntity):Long
}