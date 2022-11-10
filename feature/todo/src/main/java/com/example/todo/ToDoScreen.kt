package com.example.todo

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coreui.ui.theme.TodocomposeTheme
import com.example.model.Task
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ToDoRoute(
    viewModel: ToDoViewModel = getViewModel()
) {
    val tasksState by viewModel.tasksUiState.collectAsState(initial = TasksUiState.Loading)

    ToDoScreen(tasksUiState = tasksState, onAddClick = {})
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ToDoScreen(tasksUiState: TasksUiState, onAddClick: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, "")
            }
        }
    ) {
        if (tasksUiState is TasksUiState.Success) {
            TaskList(tasks = tasksUiState.tasks)
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>, modifier: Modifier = Modifier) {
    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        tasks.forEach { task ->
            item {
                TaskItem(task = task, onCheck = {})
            }
        }
    }
}

@Composable
private fun TaskItem(task: Task, onCheck: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = Color.Black),
        shape = MaterialTheme.shapes.medium
    ) {
        Row {
            Column {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = task.subtitle,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = task.createdAt.formatToDate(),
                    style = MaterialTheme.typography.body2
                )
            }
            Checkbox(checked = task.isDone, onCheckedChange = onCheck)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskItemPreview() {
    TodocomposeTheme {
        TaskItem(
            task = Task(
                title = "Помыть посуду",
                subtitle = "Вымыть посуду после работы",
                createdAt = 231535241,
                isDone = false
            ),
            onCheck = {}
        )
    }
}

private fun Long.formatToDate(): String {
    val sdf = SimpleDateFormat("H:m d MMM, y", Locale.getDefault())
    return sdf.format(this)
}