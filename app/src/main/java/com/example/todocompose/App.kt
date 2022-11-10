package com.example.todocompose

import android.app.Application
import com.example.data.di.dataModule
import com.example.database.di.databaseModule
import com.example.todo.di.taskModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(dataModule, databaseModule, taskModule)
        }
    }
}