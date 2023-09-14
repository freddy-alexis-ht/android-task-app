package com.sunday.taskapp.ui.task_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
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
        TitleDescriptionColumn(task, Modifier.weight(1f))
        BinIcon(Alignment.Center)
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
fun TitleDescriptionColumn(task: Task, modifier: Modifier) {
    Column(
        modifier = modifier
            .clickable { Log.i("MyTag", "Click en el task para editar") }
    ) {
        Text(
            text = task.title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(6.dp)
        )
        Text(
            text = task.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(6.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun BinIcon(alignment: Alignment) {
    Box(contentAlignment = alignment) {
        IconButton(onClick = { Log.i("MyTag", "Click en borrar") }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}
