package com.sunday.taskapp.ui.task_list

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sunday.taskapp.data.Task

//@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFD7CCC8)
@Composable
fun TaskListScreen(listVM: TaskListVM) {

    Scaffold(
        content = { Content(listVM) },
        floatingActionButton = { AddFAB() },
        floatingActionButtonPosition = FabPosition.End
    )
}

@Composable
fun Content(listVM: TaskListVM) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp)
    ) {
        items(listVM.listState ) { task ->
            TaskItem(task = task, listVM = listVM)
        }
    }
}

@Composable
fun AddFAB() {
    FloatingActionButton(
        onClick = { Log.i("MyTag", "Click en el botón +") },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add task")
    }
}