package com.sunday.taskapp.ui.add_edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.taskapp.data.Task
import com.sunday.taskapp.data.Tasks
import com.sunday.taskapp.util.CrossEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditTaskVM: ViewModel() {

    var taskId by mutableStateOf<Int>(0)

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
        if(title.isBlank()) {
            sendEvent(CrossEvent.ShowSnackbar("El título no debe estar vacío."))
            return
        }
        val currentTaskIndex = Tasks.getTasks().last().id
        if(taskId == -1){
            Tasks.insertTask(Task(
                id = currentTaskIndex + 1,
                title = title,
                description = description,
                isChecked = false
            ))
        } else {
            Tasks.updateTask(Task(
                id = taskId,
                title = title,
                description = description,
                isChecked = Tasks.getTaskById(taskId).isChecked
            ))
        }
        sendEvent(CrossEvent.NavigateBack)
    }

    fun updateTitleAndDescription(title: String, description: String) {
        this.title = title
        this.description = description
    }
}