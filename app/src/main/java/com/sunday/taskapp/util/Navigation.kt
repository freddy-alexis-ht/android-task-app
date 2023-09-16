package com.sunday.taskapp.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sunday.taskapp.ui.add_edit_task.AddEditTaskScreen
import com.sunday.taskapp.ui.add_edit_task.AddEditTaskVM
import com.sunday.taskapp.ui.task_list.TaskListScreen
import com.sunday.taskapp.ui.task_list.TaskListVM

@Composable
fun Navigation(listVM: TaskListVM, addEditVW: AddEditTaskVM) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.TASK_LIST
    ) {
        composable(route = Routes.TASK_LIST) {
            TaskListScreen(
                listVM = listVM,
                navigateTo = { // it: CrossEvent.NavigateTo
                    navController.navigate(it.route)
                }
            )
        }
        composable(
            route = Routes.ADD_EDIT_TASK+"?taskId={taskId}",
            arguments = listOf(
                navArgument(name = "taskId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val taskId = it.arguments?.getInt("taskId")
            AddEditTaskScreen(
                addEditVW = addEditVW,
                navigateBack = {
                    navController.popBackStack()
                }, taskId = taskId
            )
        }
    }
}
