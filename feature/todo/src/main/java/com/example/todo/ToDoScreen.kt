package com.example.todo

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.coreui.ui.theme.TodocomposeTheme
import com.example.model.Task
import org.koin.androidx.compose.getViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ToDoRoute(
    navController: NavController,
    viewModel: ToDoViewModel = getViewModel()
) {
    val tasksState by viewModel.tasksUiState.collectAsState(initial = TasksUiState.Loading)
    var dialogState by rememberSaveable { mutableStateOf(false) }

    ToDoScreen(tasksUiState = tasksState, onAddClick = { dialogState = true })

    ShowAddDialog(
        showDialog = dialogState,
        onDismiss = { dialogState = false },
        onSaveTask = { task ->
            dialogState = false
            viewModel.insertTask(task)
        }
    )
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

@Composable
fun ShowAddDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSaveTask: (Task) -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            AddTaskContainer(
                onDismiss = onDismiss,
                onSaveTask = onSaveTask
            )
        }
    }
}

@Composable
fun AddTaskContainer(
    onDismiss: () -> Unit,
    onSaveTask: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    var title by rememberSaveable { mutableStateOf("") }
    var subtitle by rememberSaveable { mutableStateOf("") }
    var isDone by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier
        .background(color = MaterialTheme.colors.background)
        .padding(48.dp)) {
        var isTitleEmpty by rememberSaveable { mutableStateOf(false) }
        var isSubtitleEmpty by rememberSaveable { mutableStateOf(false) }

        Column {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = stringResource(id = R.string.task_title)) },
                    isError = isTitleEmpty
                )
                Spacer(modifier = Modifier.heightIn(16.dp))
                TextField(
                    value = subtitle,
                    onValueChange = { subtitle = it },
                    label = { Text(text = stringResource(id = R.string.task_subtitle)) },
                    isError = isSubtitleEmpty
                )
                Spacer(modifier = Modifier.heightIn(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.task_is_done), style = MaterialTheme.typography.h1)
                    Spacer(modifier = Modifier.widthIn(16.dp))
                    Checkbox(checked = isDone, onCheckedChange = { isDone = it })
                }
            }
            Spacer(modifier = Modifier.heightIn(24.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = onDismiss) {
                    Text(text = stringResource(id = R.string.cancel_task))
                }
                Spacer(modifier = Modifier.widthIn(24.dp))
                Button(
                    onClick = {
                        if (title.isBlank()) {
                            isTitleEmpty = true
                        } else if (subtitle.isBlank()) {
                            isSubtitleEmpty = true
                        } else {
                            val task = Task(title = title, subtitle = subtitle, isDone = isDone)
                            onSaveTask(task)
                        }
                    }
                ) {
                    Text(text = stringResource(id = R.string.add_task))
                }
            }
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
    val sdf = SimpleDateFormat("H:mm d MMM, y", Locale.getDefault())
    return sdf.format(this)
}