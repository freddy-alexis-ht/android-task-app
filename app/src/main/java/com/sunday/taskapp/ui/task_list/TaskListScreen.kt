package com.sunday.taskapp.ui.task_list

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sunday.taskapp.data.Task
import com.sunday.taskapp.util.CrossEvent

@Composable
fun TaskListScreen(
    listVM: TaskListVM,
    navigateTo: (CrossEvent.NavigateTo) -> Unit
) {

    var context = LocalContext.current
    listVM.useContextToInitTaskDataStore(context)
    listVM.xx()

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        listVM.crossEvent.collect { event ->
            when (event) {
                is CrossEvent.NavigateTo -> navigateTo(event)
                is CrossEvent.ShowSnackbar -> {
                    val undoClicked = scaffoldState.snackbarHostState
                        .showSnackbar(event.message, event.action)
                    if(undoClicked == SnackbarResult.ActionPerformed) {
                        listVM.onEvent(TaskListEvent.OnUndoDeleteInSnackbar)
                    }
                }
                //CrossEvent.NavigateBack -> // It's not used in screen-1
                else -> Unit
            }
        }
    }

    Scaffold(
        content = { Content(listVM) },
        floatingActionButton = { AddFAB(listVM) },
        floatingActionButtonPosition = FabPosition.End,
        scaffoldState = scaffoldState
    )
}

@Composable
fun Content(listVM: TaskListVM) {
//    Log.i("MyTag", "Content .. inicio")
//    var m: MutableList<Task>? = listVM.listState
//    Log.i("MyTag", "Content .. ${m?.size}")
//    var n: MutableList<Task> = if(m == null) mutableListOf() else m!!
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp)
    ) {
        items(listVM.listState!!) { task ->
            TaskItem(
                task = task,
                listVM = listVM,
            )
        }
    }
}

@Composable
fun AddFAB(listVM: TaskListVM) {
    FloatingActionButton(
        onClick = {
            listVM.onEvent(TaskListEvent.OnAddButton)
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add task")
    }
}