package com.sunday.taskapp.ui.task_list

import android.util.Log
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.taskapp.data.Task
import com.sunday.taskapp.data.getTasks
import com.sunday.taskapp.util.CrossEvent
import com.sunday.taskapp.util.Routes
import kotlinx.coroutines.launch

class TaskListVM: ViewModel() {

    var listState by mutableStateOf(getTasks())
        private set

    fun onEvent(event: TaskListEvent) {
        when(event) {
            is TaskListEvent.OnCheckBox -> onCheckBox(event.task, event.isChecked)
            is TaskListEvent.OnTaskItem -> TODO()
            is TaskListEvent.OnAddButton -> onAddButton(event.navigateTo)
            is TaskListEvent.OnDeleteIcon -> onDeleteIcon(event.task, event.scaffoldState)
            TaskListEvent.OnUndoDeleteInSnackbar -> TODO()
        }
    }

    private fun onCheckBox(task: Task, isChecked: Boolean) {
        val indexToChange = task.index
        listState.set(indexToChange, task.copy(isChecked = isChecked))
    }

    private fun onAddButton(navigateTo: (CrossEvent.NavigateTo) -> Unit) {
        run {navigateTo(CrossEvent.NavigateTo(Routes.ADD_EDIT_TASK))}
    }

    private fun onDeleteIcon(task: Task, scaffoldState: ScaffoldState) {
        val showSnackbar = CrossEvent.ShowSnackbar("Hola Lucas", "Pasear")
        viewModelScope.launch {
            scaffoldState.snackbarHostState
                .showSnackbar(showSnackbar.message, showSnackbar.action)
        }
    }
}