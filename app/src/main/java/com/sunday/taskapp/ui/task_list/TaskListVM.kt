package com.sunday.taskapp.ui.task_list

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.sunday.taskapp.data.Task
import com.sunday.taskapp.data.getTasks

class TaskListVM: ViewModel() {

    var listState by mutableStateOf(getTasks())
        private set

    fun onEvent(event: TaskListEvent) {
        when(event) {
            is TaskListEvent.OnCheckBox -> onCheckBox(event.task, event.isChecked)
            is TaskListEvent.OnTaskItem -> TODO()
            TaskListEvent.OnAddButton -> TODO()
            is TaskListEvent.OnDeleteIcon -> TODO()
            TaskListEvent.OnUndoDeleteInSnackbar -> TODO()
        }
    }

    private fun onCheckBox(task: Task, isChecked: Boolean) {
        val indexToChange = task.index
        listState.set(indexToChange, task.copy(isChecked = isChecked))
    }

}