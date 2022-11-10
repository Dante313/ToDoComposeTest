package com.example.data.repository.task

import com.example.data.extention.asEntity
import com.example.data.extention.asExternalModel
import com.example.database.dao.TaskDao
import com.example.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {

    override fun getTaskEntityStream(taskId: Long): Flow<Task> {
        return taskDao.getTaskEntityStream(taskId).map { taskEntity ->
            taskEntity.asExternalModel()
        }
    }

    override fun getTasksEntityStream(): Flow<List<Task>> {
        return taskDao.getTasksEntityStream().map { taskEntities ->
            taskEntities.map { taskEntity ->
                taskEntity.asExternalModel()
            }
        }
    }

    override suspend fun insertTask(task: Task): Long = taskDao.insertTask(task.asEntity())

    override suspend fun updateTask(task: Task) = taskDao.updateTask(task.asEntity())

    override suspend fun deleteTask(task: Task) = taskDao.deleteTask(task.asEntity())

    override suspend fun deleteAllTasks() = taskDao.deleteAllTasks()
}