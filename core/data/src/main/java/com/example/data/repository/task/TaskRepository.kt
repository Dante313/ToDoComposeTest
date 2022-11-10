package com.example.data.repository.task

import com.example.database.model.TaskEntity
import com.example.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTaskEntityStream(taskId: Long): Flow<Task>

    fun getTasksEntityStream(): Flow<List<Task>>

    suspend fun insertTask(task: Task): Long

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun deleteAllTasks()
}