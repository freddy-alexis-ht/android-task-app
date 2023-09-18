package com.sunday.taskapp.ui.task_list

import android.content.Context
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunday.taskapp.data.*
import com.sunday.taskapp.util.CrossEvent
import com.sunday.taskapp.util.Routes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking

class TaskListVM: ViewModel() {

    private var repo: TaskRepository = TaskProvider()
//    private var repo2: TaskRepository? = null
    private var repo2: TaskDataStore? = null
//    private lateinit var repo2: TaskRepository

    fun useContextToInitTaskDataStore(context: Context) {
        repo2 = TaskDataStore(context)

        var x: MutableList<Task>? = repo2?.getTasks2()
        if(x == null) Log.i("MyTag", "x es null: $x")
        else Log.i("MyTag", "x no es null: $x")
        x!!.forEach { Log.i("MyTag", "x no es null: ${it.title}") }
    }

    fun xx() {
        var x: MutableList<Task>? = mutableListOf()
        val job = GlobalScope.launch {
            x = repo2?.getTasks2()
        }
        runBlocking {
            job.join()
            return@runBlocking x
        }
        listState = x
    }
//    var tasks: MutableList<Task> = mutableListOf()
//    var tasks: MutableList<Task>? = repo2?.getTasks()

    var listState: MutableList<Task>? by mutableStateOf(null)
        private set
//    var listState by mutableStateOf(repo.getTasks())
//        private set
//    var listState2 = repo2?.getTasks()
//    var lista = repo2.getTasks()
//    var listState2 by mutableStateOf(repo2.getTasks())
//        private set

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
        repo.updateTask(task.copy(isChecked = isChecked))
    }

    private fun onTaskItem(task: Task) {
        sendEvent(CrossEvent.NavigateTo(route = Routes.ADD_EDIT_TASK + "?taskId=${task.id}"))
    }

    private fun onAddButton() {
        sendEvent(CrossEvent.NavigateTo(route = Routes.ADD_EDIT_TASK))
    }

    private fun onDeleteIcon(task: Task) {
        this.deletedTask = task
        this.deletedIndex = listState?.indexOf(task)
        repo.deleteTask(task)
        this.sendEvent(CrossEvent.ShowSnackbar("Tarea borrada", "DESHACER"))
    }

    private fun onUndoDeleteInSnackbar() {
        repo.insertTask(deletedIndex!!,deletedTask!!)
    }
}