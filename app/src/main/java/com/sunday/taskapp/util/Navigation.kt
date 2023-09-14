package com.sunday.taskapp.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sunday.taskapp.ui.add_edit_task.AddEditTaskScreen
import com.sunday.taskapp.ui.task_list.TaskListScreen
import com.sunday.taskapp.ui.task_list.TaskListVM

@Composable
fun Navigation(listVM: TaskListVM) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.TASK_LIST
    ) {
        composable(route = Routes.TASK_LIST) {
            TaskListScreen(
                listVM = listVM,
                navigate = {
                    navController.navigate(Routes.ADD_EDIT_TASK)
                }
            )
        }
        composable(route = Routes.ADD_EDIT_TASK) {
            AddEditTaskScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
