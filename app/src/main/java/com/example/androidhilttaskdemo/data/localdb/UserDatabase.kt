package com.example.androidhilttaskdemo.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidhilttaskdemo.data.constant.Constant.USER_DATABASE

// Database class Room Database Initialise
@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDaoInterface

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    USER_DATABASE
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}