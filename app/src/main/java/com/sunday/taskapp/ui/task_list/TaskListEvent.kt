package com.sunday.taskapp.ui.task_list

import androidx.compose.material.ScaffoldState
import com.sunday.taskapp.data.Task
import com.sunday.taskapp.util.CrossEvent

sealed class TaskListEvent {
    data class OnCheckBox(val task: Task, val isChecked: Boolean): TaskListEvent()
    data class OnTaskItem(val task: Task): TaskListEvent()
    object OnAddButton: TaskListEvent()
    data class OnDeleteIcon(val task: Task): TaskListEvent()
    object OnUndoDeleteInSnackbar: TaskListEvent()
}