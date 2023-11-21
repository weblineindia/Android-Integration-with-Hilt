package com.example.androidhilttaskdemo.hilt

import android.app.Application

import dagger.hilt.android.HiltAndroidApp

// Application class
@HiltAndroidApp //Annotation to declare Hilt Application
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}