package com.example.androidhilttaskdemo.model

// ToDoModel's data class OR Model class
data class ToDoModelItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)