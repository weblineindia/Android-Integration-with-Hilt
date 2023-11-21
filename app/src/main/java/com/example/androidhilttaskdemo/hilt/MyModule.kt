package com.example.androidhilttaskdemo.hilt

import android.content.Context
import com.example.androidhilttaskdemo.data.api.ApiService
import com.example.androidhilttaskdemo.data.constant.Constant.BASE_URL

import com.example.androidhilttaskdemo.data.localdb.UserDaoInterface
import com.example.androidhilttaskdemo.data.localdb.UserDatabase
import com.example.androidhilttaskdemo.data.repository.ToDoRepository
import com.example.androidhilttaskdemo.data.repository.UserRepository
import com.example.androidhilttaskdemo.data.shared_pref.SharedPref
import com.example.androidhilttaskdemo.viewmodel.ToDoViewModel
import com.example.androidhilttaskdemo.viewmodel.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Module of Hilt
// @Module : Annotation to declare Module
// @InstallIn : Annotation to declare which Hilt generated DI contains
@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    // @Singleton : Identifies a type that the injector only instantiates once
    // @Provides : annotation require to return feature of each module method
    // Method to initialise UserDatabase
    @Singleton
    @Provides
    fun initUserDatabase(@ApplicationContext context: Context): UserDatabase {
        return UserDatabase.getDatabase(context)
    }

    // Method to initialise UserDatabase
    @Singleton
    @Provides
    fun getUserDao(userDatabase: UserDatabase): UserDaoInterface {
        return userDatabase.userDao();
    }

    // Method to initialise SharedPref
    @Singleton
    @Provides
    fun getSharedPref(@ApplicationContext context: Context): SharedPref {
        return SharedPref(context);
    }

    // Method to initialise UserViewModel
    @Singleton
    @Provides
    fun getUserViewModel(userRepository: UserRepository, preference: SharedPref): UserViewModel {
        return UserViewModel(userRepository, preference);
    }

    // Method to initialise ToDoViewModel
    @Singleton
    @Provides
    fun getToDoViewModel(todoRepository: ToDoRepository): ToDoViewModel {
        return ToDoViewModel(todoRepository)
    }

    // Method to initialise Retrofit
    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Method to initialise ApiService
    @Singleton
    @Provides
    fun getService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }
}