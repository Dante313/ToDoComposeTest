package com.example.data.extention

import com.example.database.model.TaskEntity
import com.example.model.Task

fun Task.asEntity() = TaskEntity(
    id = id,
    title = title,
    subtitle = subtitle,
    createdAt = createdAt,
    isDone = isDone
)

fun TaskEntity.asExternalModel() = Task(
    id = id,
    title = title,
    subtitle = subtitle,
    createdAt = createdAt,
    isDone = isDone
)