package com.sunday.taskapp.ui.add_edit_task

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.taskapp.data.Task
import com.sunday.taskapp.data.TaskDataStore
import com.sunday.taskapp.data.TaskProvider
import com.sunday.taskapp.data.TaskRepository
import com.sunday.taskapp.util.CrossEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditTaskVM: ViewModel() {
    private var repo: TaskRepository = TaskProvider()
//    private lateinit var repo2: TaskRepository
//    private lateinit var repo2: TaskDataStore

//    fun useContextToInitTaskDataStore(context: Context) {
//        Log.i("MyTag", "Context: $context")
//        repo2 =  TaskDataStore(context)
//        Log.i("MyTag", "Repo2: $repo2")
//    }

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
        val currentTaskIndex = repo.getTasks().last().id
        if(taskId == -1){
            repo.insertTask(Task(
                id = currentTaskIndex + 1,
                title = title,
                description = description,
                isChecked = false
            ))
//            repo2.insertTask(Task(
//                id = currentTaskIndex + 1,
//                title = title,
//                description = description,
//                isChecked = false
//            ))
//            repo2.getTasks()
        } else {
            repo.updateTask(Task(
                id = taskId,
                title = title,
                description = description,
                isChecked = repo.getTaskById(taskId).isChecked
            ))
        }
        sendEvent(CrossEvent.NavigateBack)
    }

    fun updateTitleAndDescription(title: String, description: String) {
        this.title = title
        this.description = description
    }
}