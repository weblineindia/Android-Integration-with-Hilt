package com.example.androidhilttaskdemo.data.api

import com.example.androidhilttaskdemo.model.ToDoModel
import retrofit2.Call
import retrofit2.http.GET

// API methods
interface  ApiService {

    @GET("todos")
    fun getAllToDo(): Call<ToDoModel>
}