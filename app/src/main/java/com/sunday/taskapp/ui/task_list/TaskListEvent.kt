package com.sunday.taskapp.ui.task_list

import com.sunday.taskapp.data.Task

sealed class TaskListEvent {
    object OnCheckBox: TaskListEvent()
    data class OnTaskItem(val task: Task): TaskListEvent()
    object OnAddButton: TaskListEvent()
    object OnDeleteIcon: TaskListEvent()
    object OnUndoDeleteInSnackbar: TaskListEvent()
}