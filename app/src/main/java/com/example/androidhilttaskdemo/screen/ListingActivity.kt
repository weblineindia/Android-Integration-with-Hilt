package com.example.androidhilttaskdemo.screen

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.androidhilttaskdemo.data.shared_pref.SharedPref
import com.example.androidhilttaskdemo.databinding.ActivityListingBinding
import com.example.androidhilttaskdemo.model.ToDoModelItem
import com.example.androidhilttaskdemo.viewmodel.ToDoViewModel
import com.example.androidhilttaskdemo.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Listing Activity
// @AndroidEntryPoint : enable members injection in activity
@AndroidEntryPoint
class ListingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListingBinding

    // @Inject : annotation lets us define an injection point that is injected during bean instantiation
    @Inject
    lateinit var toDoViewModel: ToDoViewModel

    // @Inject : annotation lets us define an injection point that is injected during bean instantiation
    @Inject
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myToDoList = mutableListOf<String>()

        // invoke method to fetch todos
        toDoViewModel.getToDo()

        toDoViewModel.todoList.observe(this) {
            for (i: ToDoModelItem in it) {
                myToDoList.add(i.title)
            }
            binding.myList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myToDoList)
        }

        binding.logoutBtn.setOnClickListener {
            userViewModel.setLoginState(false)
            startActivity(Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
            this.finish()
        }
    }
}