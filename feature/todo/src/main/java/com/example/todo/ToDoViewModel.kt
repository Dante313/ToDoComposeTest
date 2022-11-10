package com.example.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.result.*
import com.example.data.repository.task.TaskRepository
import com.example.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ToDoViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    val tasksUiState: Flow<TasksUiState> = tasksUiStateStream()

    private fun tasksUiStateStream(): Flow<TasksUiState> {
        return taskRepository.getTasksEntityStream().asResult().map { tasksResult ->
            when (tasksResult) {
                is Result.Success -> TasksUiState.Success(tasksResult.data)
                is Result.Loading -> TasksUiState.Loading
                is Result.Error -> TasksUiState.Error
            }
        }
    }

    private fun insertTask(task: Task) {
        viewModelScope.launch { taskRepository.insertTask(task) }
    }

    private fun updateTask(task: Task) {
        viewModelScope.launch { taskRepository.updateTask(task) }
    }

    private fun deleteTask(task: Task) {
        viewModelScope.launch { taskRepository.deleteTask(task) }
    }

    private fun deleteAllTasks() {
        viewModelScope.launch { taskRepository.deleteAllTasks() }
    }
}

sealed interface TasksUiState {
    data class Success(val tasks: List<Task>) : TasksUiState
    object Error : TasksUiState
    object Loading : TasksUiState
}