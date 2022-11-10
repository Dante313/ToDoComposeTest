package com.example.model

data class Task(
    val id: Long = 0,
    val title: String,
    val subtitle: String,
    val createdAt: Long = System.currentTimeMillis(),
    val isDone: Boolean,
)