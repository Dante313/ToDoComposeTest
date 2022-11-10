package com.example.data.di

import com.example.data.repository.task.TaskRepository
import com.example.data.repository.task.TaskRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    factory<TaskRepository> { TaskRepositoryImpl(get()) }
}