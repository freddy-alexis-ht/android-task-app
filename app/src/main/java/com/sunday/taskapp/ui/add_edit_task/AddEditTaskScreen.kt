package com.sunday.taskapp.ui.add_edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sunday.taskapp.data.TaskProvider
import com.sunday.taskapp.data.TaskRepository
import com.sunday.taskapp.util.CrossEvent

@Composable
fun AddEditTaskScreen(
    addEditVW: AddEditTaskVM,
    navigateBack: () -> Unit,
    taskId: Int?
) {

    val scaffoldState = rememberScaffoldState()
    addEditVW.taskId = taskId!!

    LaunchedEffect(key1 = true) {
        addEditVW.crossEvent.collect { event ->
            when (event) {
                CrossEvent.NavigateBack -> navigateBack()
                is CrossEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                // is CrossEvent.NavigateTo -> // is not used in screen-2
                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier.padding(16.dp),
        content = { Content(addEditVW, taskId) },
        floatingActionButton = { SaveFAB(addEditVW) },
        floatingActionButtonPosition = FabPosition.End,
        scaffoldState = scaffoldState
    )
}

@Composable
fun Content(addEditVW: AddEditTaskVM, taskId: Int) {

    LaunchedEffect(Unit) {
        val repo: TaskRepository = TaskProvider()
        if (taskId == -1)
            addEditVW.updateTitleAndDescription("", "")
        else {
            val taskSent = repo.getTaskById(taskId)
            addEditVW.updateTitleAndDescription(taskSent.title, taskSent.description ?: ""
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 12.dp)
    ) {
        TextField(
            value = addEditVW.title,
            onValueChange = { addEditVW.onEvent(AddEditTaskEvent.OnTitleChange(it)) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Título") },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = addEditVW.description,
            onValueChange = { addEditVW.onEvent(AddEditTaskEvent.OnDescriptionChange(it)) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Descripción") },
            maxLines = 5,
            singleLine = false,
        )
    }
}

@Composable
fun SaveFAB(addEditVW: AddEditTaskVM) {
    val context = LocalContext.current
    FloatingActionButton(onClick = {
//        addEditVW.useContextToInitTaskDataStore(context)
        addEditVW.onEvent(AddEditTaskEvent.OnSaveButton)
    }) {
        Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
    }
}
