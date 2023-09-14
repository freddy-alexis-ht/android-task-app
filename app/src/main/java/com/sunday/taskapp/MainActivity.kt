package com.sunday.taskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sunday.taskapp.ui.add_edit_task.AddEditTaskScreen
import com.sunday.taskapp.ui.task_list.TaskListScreen
import com.sunday.taskapp.ui.task_list.TaskListVM
import com.sunday.taskapp.ui.theme.TaskAppTheme

class MainActivity : ComponentActivity() {

    private val listVM:TaskListVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TaskListScreen(listVM = listVM)
//                    AddEditTaskScreen()
                }
            }
        }
    }
}
