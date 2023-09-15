package com.sunday.taskapp.ui.add_edit_task

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.taskapp.data.Task
import com.sunday.taskapp.ui.task_list.TaskListVM
import com.sunday.taskapp.util.CrossEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditTaskVM: ViewModel() {

    var listVM = TaskListVM()

    var task by mutableStateOf<Task?>(null)
        private set

    var title by mutableStateOf<String>("")
        private set
    var description by mutableStateOf<String>("")
        private set

    private val _crossEvent = Channel<CrossEvent>()
    val crossEvent = _crossEvent.receiveAsFlow()

    private fun sendEvent(event: CrossEvent) {
        viewModelScope.launch {
            _crossEvent.send(event)
        }
    }

    fun onEvent(event: AddEditTaskEvent) {
        when(event) {
            is AddEditTaskEvent.OnTitleChange -> onTitleChange(event.title)
            is AddEditTaskEvent.OnDescriptionChange -> onDescriptionChange(event.description)
            AddEditTaskEvent.OnSaveButton -> onSaveButton()
        }
    }

    private fun onTitleChange(title: String) {
        this.title = title
    }

    private fun onDescriptionChange(description: String) {
        this.description = description
    }

    private fun onSaveButton() {
        val currentTaskIndex = listVM.listState.last().index
        listVM.listState.add(Task(index = currentTaskIndex+1, title, description, false))
        sendEvent(CrossEvent.NavigateBack)
    }
}