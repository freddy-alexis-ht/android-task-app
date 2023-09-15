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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class TaskListVM: ViewModel() {

    var listState by mutableStateOf(getTasks())
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

    fun onEvent(event: TaskListEvent) {
        when(event) {
            is TaskListEvent.OnCheckBox -> onCheckBox(event.task, event.isChecked)
            is TaskListEvent.OnTaskItem -> TODO()
            TaskListEvent.OnAddButton -> onAddButton()
            is TaskListEvent.OnDeleteIcon -> onDeleteIcon(event.task)
            TaskListEvent.OnUndoDeleteInSnackbar -> onUndoDeleteInSnackbar()
        }
    }

    private fun onCheckBox(task: Task, isChecked: Boolean) {
        val indexToChange = task.index
        listState.set(indexToChange, task.copy(isChecked = isChecked))
    }

    private fun onAddButton() {
        sendEvent(CrossEvent.NavigateTo(route = Routes.ADD_EDIT_TASK))
    }

    private fun onDeleteIcon(task: Task) {
        this.deletedTask = task
        this.listState.remove(task)
        this.sendEvent(CrossEvent.ShowSnackbar("Task borrado", "DESHACER"))
    }

    private fun onUndoDeleteInSnackbar() {
        val undoIndex = this.deletedTask?.index
        this.listState.add(undoIndex!!,deletedTask!!)
    }
}