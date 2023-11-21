package com.example.androidhilttaskdemo.data.repository

import com.example.androidhilttaskdemo.data.api.ApiService
import com.example.androidhilttaskdemo.model.ToDoModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

// Repository of API
class ToDoRepository @Inject constructor(private val apiService: ApiService) {

    // Method to fetch data
    fun fetchList(): Call<ToDoModel> {
        return apiService.getAllToDo();
    }
}