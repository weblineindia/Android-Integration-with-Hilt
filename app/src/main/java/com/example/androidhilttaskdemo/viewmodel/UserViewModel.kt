package com.example.androidhilttaskdemo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.androidhilttaskdemo.data.localdb.UserEntity
import com.example.androidhilttaskdemo.data.repository.UserRepository
import com.example.androidhilttaskdemo.data.shared_pref.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository, private val preference: SharedPref) : ViewModel() {

    // Method to validate, user registered or not
    fun validateUser(username: String, password: String): Boolean {
        val user = userRepository.getUser(username) ?: return false
        return user.password == password && user.username == username
    }

    // Method to register new user
    fun registerUser(user: UserEntity): Long {
        return userRepository.registerUser(user)
    }

    // Method to set logged in status
    fun setLoginState(flag: Boolean) {
        return preference.setLoginState(flag)
    }

    // Method to get logged in status
    fun isLoggedIn(): Boolean {
        return preference.getLoginState()
    }
}