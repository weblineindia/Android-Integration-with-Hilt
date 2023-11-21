package com.example.androidhilttaskdemo.data.shared_pref

import android.content.Context
import android.content.SharedPreferences
import com.example.androidhilttaskdemo.data.constant.Constant.IS_LOGGED
import com.example.androidhilttaskdemo.data.constant.Constant.MY_SHARED_PREFERENCE
import com.example.androidhilttaskdemo.data.localdb.UserEntity
import com.google.gson.Gson
import javax.inject.Inject

// Shared Preference class
class SharedPref @Inject constructor(context: Context) {

    private val sharedPreference: SharedPreferences = context.getSharedPreferences(MY_SHARED_PREFERENCE, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreference.edit()
    private val gson: Gson = Gson()

    // Method to store in Shared Preference (Store user)
    fun storeValue(key: String, value: Any) {
        if (value is UserEntity) {
            val user = gson.toJson(value)
            editor.putString(key, user)
            editor.commit()
        }
    }

    // Method to set login state (loggedIn true or false)
    fun setLoginState(flag: Boolean) {
        editor.putBoolean(IS_LOGGED, flag)
        editor.commit()
    }

    // Method to get login state
    fun getLoginState(): Boolean {
        return sharedPreference.getBoolean(IS_LOGGED, false)
    }
}