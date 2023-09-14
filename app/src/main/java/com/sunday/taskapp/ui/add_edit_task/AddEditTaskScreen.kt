package com.sunday.taskapp.ui.add_edit_task

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sunday.taskapp.util.CrossEvent

@Composable
fun AddEditTaskScreen(
    addEditVW: AddEditTaskVM,
    navigateBack: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        addEditVW.crossEvent.collect { event ->
            when(event) {
                CrossEvent.NavigateBack -> navigateBack()
                is CrossEvent.ShowSnackbar -> TODO()
                // is CrossEvent.NavigateTo -> // is not used in screen-2
                else -> Unit
            }
        }
    }

    Scaffold(
        modifier = Modifier.padding(16.dp),
        content = { Content() },
        floatingActionButton = { SaveFAB(addEditVW) },
        floatingActionButtonPosition = FabPosition.End,
        scaffoldState = scaffoldState
    )
}

@Composable
fun Content() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 12.dp)) {
        TextField(
            value = "",
            onValueChange = { Log.i("MyTag", "Writing in title text-field") },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Título") },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = "",
            onValueChange = { Log.i("MyTag", "Writing in description text-field") },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Descripción") },
            maxLines = 5,
            singleLine = false,
        )
    }
}

@Composable
fun SaveFAB(addEditVW: AddEditTaskVM) {
    FloatingActionButton(onClick = {
        addEditVW.onEvent(AddEditTaskEvent.OnSaveButton)
    }) {
        Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
    }
}
