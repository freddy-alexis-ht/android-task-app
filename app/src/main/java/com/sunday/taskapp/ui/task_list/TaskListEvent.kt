package com.sunday.taskapp.ui.task_list

import androidx.compose.material.ScaffoldState
import com.sunday.taskapp.data.Task
import com.sunday.taskapp.util.CrossEvent

sealed class TaskListEvent {
    data class OnCheckBox(val task: Task, val isChecked: Boolean): TaskListEvent()
    data class OnTaskItem(val task: Task): TaskListEvent()
    object OnAddButton: TaskListEvent()
//    data class OnAddButton(val navigateTo: (CrossEvent.NavigateTo) -> Unit) : TaskListEvent()
    data class OnDeleteIcon(val task: Task, val scaffoldState: ScaffoldState): TaskListEvent()
    object OnUndoDeleteInSnackbar: TaskListEvent()
}