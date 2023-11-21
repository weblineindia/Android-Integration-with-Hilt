package com.example.androidhilttaskdemo.screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhilttaskdemo.R
import com.example.androidhilttaskdemo.data.repository.ToDoRepository
import com.example.androidhilttaskdemo.data.shared_pref.SharedPref
import com.example.androidhilttaskdemo.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isLogged = userViewModel.isLoggedIn()

        if (isLogged) {
            startActivity(Intent(this, ListingActivity::class.java))
            finish()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.main_frame, LoginFragment()).disallowAddToBackStack().commit()
        }
    }
}