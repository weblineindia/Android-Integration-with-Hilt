package com.example.androidhilttaskdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidhilttaskdemo.data.repository.ToDoRepository
import com.example.androidhilttaskdemo.model.ToDoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

// @HiltViewModel : enable injection of a ViewModel by Hilt
@HiltViewModel
class ToDoViewModel @Inject constructor(private val toDoRepository: ToDoRepository) : ViewModel() {

    val todoList = MutableLiveData<ToDoModel>()

    // Method to fetch todos
    fun getToDo() {
        viewModelScope.launch(Dispatchers.IO) {
            val todos = toDoRepository.fetchList();

            todos.enqueue(object : Callback<ToDoModel?> {
                override fun onResponse(call: Call<ToDoModel?>, response: Response<ToDoModel?>) {
                    if (response.isSuccessful && response.body() != null) {
                        todoList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ToDoModel?>, t: Throwable) {

                }
            })
        }
    }
}