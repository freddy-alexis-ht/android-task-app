package com.sunday.taskapp.ui.task_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sunday.taskapp.data.Task

@Composable
fun TaskItem(task: Task, listVM: TaskListVM) {
    TaskRow(task, listVM)
}

@Composable
fun TaskRow(task: Task, listVM: TaskListVM) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DoneCheckBox(task, listVM, Alignment.Center)
        TitleDescriptionColumn(task, listVM, Modifier.weight(1f))
        BinIcon(task, listVM, Alignment.Center)
    }
}
@Composable
fun DoneCheckBox(task: Task, listVM: TaskListVM, alignment: Alignment) {
    Box(contentAlignment = alignment) {
        Checkbox(
            checked = task.isChecked,
            onCheckedChange = { isChecked -> // it: Boolean
                listVM.onEvent(TaskListEvent.OnCheckBox(task, isChecked))
            }
        )
    }
}

@Composable
fun TitleDescriptionColumn(task: Task, listVM: TaskListVM, modifier: Modifier) {
    Column(
        modifier = modifier
            .clickable { listVM.onEvent(TaskListEvent.OnTaskItem(task)) }
    ) {
        Text(
            text = task.title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(6.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        task.description?.let {
            Text(
                text = it,
                fontSize = 16.sp,
                modifier = Modifier.padding(6.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun BinIcon(task: Task, listVM: TaskListVM, alignment: Alignment) {

    Box(contentAlignment = alignment) {
        IconButton(onClick = {
            listVM.onEvent(TaskListEvent.OnDeleteIcon(task))
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
