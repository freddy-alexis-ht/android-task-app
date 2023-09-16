package com.sunday.taskapp.ui.task_list

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.taskapp.data.*
import com.sunday.taskapp.util.CrossEvent
import com.sunday.taskapp.util.Routes
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class TaskListVM: ViewModel() {
    var listState by mutableStateOf(Tasks.getTasks())
        private set

    // kotlinx.coroutines.channels.Channel
    private val _crossEvent = Channel<CrossEvent>()
    val crossEvent = _crossEvent.receiveAsFlow()

    private fun sendEvent(event: CrossEvent) {
        viewModelScope.launch {
            _crossEvent.send(event)
        }
    }

    private var deletedTask: Task? = null
    private var deletedIndex: Int? = null

    fun onEvent(event: TaskListEvent) {
        when(event) {
            is TaskListEvent.OnCheckBox -> onCheckBox(event.task, event.isChecked)
            is TaskListEvent.OnTaskItem -> onTaskItem(event.task)
            TaskListEvent.OnAddButton -> onAddButton()
            is TaskListEvent.OnDeleteIcon -> onDeleteIcon(event.task)
            TaskListEvent.OnUndoDeleteInSnackbar -> onUndoDeleteInSnackbar()
        }
    }

    private fun onCheckBox(task: Task, isChecked: Boolean) {
        val indexToChange = listState.indexOf(task)
        listState.set(indexToChange, task.copy(isChecked = isChecked))
    }

    private fun onTaskItem(task: Task) {
        sendEvent(CrossEvent.NavigateTo(route = Routes.ADD_EDIT_TASK + "?taskId=${task.id}"))
    }

    private fun onAddButton() {
        sendEvent(CrossEvent.NavigateTo(route = Routes.ADD_EDIT_TASK))
    }

    private fun onDeleteIcon(task: Task) {
        this.deletedTask = task
        this.deletedIndex = listState.indexOf(task)
        this.listState.remove(task)
        this.sendEvent(CrossEvent.ShowSnackbar("Task borrado", "DESHACER"))
    }

    private fun onUndoDeleteInSnackbar() {
        this.listState.add(deletedIndex!!,deletedTask!!)
    }
}