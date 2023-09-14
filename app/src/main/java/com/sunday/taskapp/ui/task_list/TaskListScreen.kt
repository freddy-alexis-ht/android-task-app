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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sunday.taskapp.util.CrossEvent

@Composable
fun TaskListScreen(
    listVM: TaskListVM,
    navigateTo: (CrossEvent.NavigateTo) -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        content = { Content(listVM, scaffoldState) },
        floatingActionButton = { AddFAB(listVM, navigateTo) },
        floatingActionButtonPosition = FabPosition.End,
        scaffoldState = scaffoldState
    )
}

@Composable
fun Content(listVM: TaskListVM, scaffoldState: ScaffoldState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp)
    ) {
        items(listVM.listState ) { task ->
            TaskItem(
                task = task,
                listVM = listVM,
                scaffoldState = scaffoldState
            )
        }
    }
}

@Composable
fun AddFAB(listVM: TaskListVM, navigateTo: (CrossEvent.NavigateTo) -> Unit) {
    FloatingActionButton(
        onClick = {
            listVM.onEvent(TaskListEvent.OnAddButton(navigateTo))
            Log.i("MyTag", "Va a add_edit_task")
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add task")
    }
}