package com.sunday.taskapp.ui.add_edit_task

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

//@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFD7CCC8)
@Composable
fun AddEditTaskScreen(navigateBack: () -> Unit) {

    Scaffold(
        modifier = Modifier.padding(16.dp),
        content = { Content() },
        floatingActionButton = { SaveFAB(navigateBack) },
        floatingActionButtonPosition = FabPosition.End
    )
}

@Composable
fun Content() {
    Column(modifier = Modifier.fillMaxSize().padding(top = 12.dp)) {
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
fun SaveFAB(navigateBack: () -> Unit) {
    FloatingActionButton(onClick = {
        navigateBack()
        Log.i("MyTag", "Regresa a task_list")
    }) {
        Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
    }
}
