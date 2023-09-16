package com.sunday.taskapp.data

import androidx.compose.runtime.toMutableStateList

data class Task(
    var id: Int,
    var title: String,
    var description: String?,
    var isChecked: Boolean
)
