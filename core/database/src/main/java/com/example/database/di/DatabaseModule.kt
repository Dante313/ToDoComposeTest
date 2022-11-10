package com.example.database.di

import androidx.room.Room
import com.example.database.TaskDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val TASK_DB_NAME = "task_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            TaskDatabase::class.java,
            TASK_DB_NAME
        )
            .build()
            .taskDao()
    }
}