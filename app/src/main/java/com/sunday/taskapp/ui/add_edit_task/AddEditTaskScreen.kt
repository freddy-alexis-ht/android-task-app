package com.sunday.taskapp.ui.add_edit_task

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFD7CCC8)
@Composable
fun AddEditTaskScreen() {

    Scaffold(
        modifier = Modifier.padding(16.dp),
        content = { Content() },
        floatingActionButton = { SaveFAB() },
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
fun SaveFAB() {
    FloatingActionButton(onClick = { Log.i("MyTag", "Click en el botón check") }) {
        Icon(imageVector = Icons.Default.Check, contentDescription = "Save")
    }
}
