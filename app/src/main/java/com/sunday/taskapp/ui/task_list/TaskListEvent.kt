package com.sunday.taskapp.ui.task_list

import com.sunday.taskapp.data.Task

sealed class TaskListEvent {
    data class OnCheckBox(val task: Task, val isChecked: Boolean): TaskListEvent()
    data class OnTaskItem(val task: Task): TaskListEvent()
    object OnAddButton: TaskListEvent()
    data class OnDeleteIcon(val task: Task): TaskListEvent()
    object OnUndoDeleteInSnackbar: TaskListEvent()
}