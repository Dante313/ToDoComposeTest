package com.example.todo.di

import com.example.todo.ToDoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val taskModule = module {
    viewModel { ToDoViewModel(get()) }
}