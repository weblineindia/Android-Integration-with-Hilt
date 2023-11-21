package com.example.androidhilttaskdemo.data.repository

import com.example.androidhilttaskdemo.data.localdb.UserDaoInterface
import com.example.androidhilttaskdemo.data.localdb.UserEntity
import javax.inject.Inject

// Repository of Room Database
class UserRepository @Inject constructor(private val userDao: UserDaoInterface) {

    // Method to fetch (Fetch stored User on Login)
    fun getUser(username: String): UserEntity? {
        return userDao.getUser(username)
    }

    // Method to insert (Store user on Signup)
    fun registerUser(userEntity: UserEntity): Long {
        val resValue = userDao.registerUser(userEntity);
        return resValue;
    }
}