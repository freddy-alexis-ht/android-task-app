package com.sunday.taskapp.ui.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.taskapp.data.Task
import com.sunday.taskapp.util.CrossEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditTaskVM: ViewModel() {

    var task by mutableStateOf<Task?>(null)
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
            is AddEditTaskEvent.OnDescriptionChange -> TODO()
            is AddEditTaskEvent.OnTitleChange -> TODO()
            AddEditTaskEvent.OnSaveButton -> onSaveButton()
        }
    }

    private fun onSaveButton() {
        sendEvent(CrossEvent.NavigateBack)
    }
}