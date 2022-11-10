package com.example.coreui.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.coreui.R

object ToDoIcons {
    val Task = R.drawable.ic_task
    val Settings = R.drawable.ic_settings
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}