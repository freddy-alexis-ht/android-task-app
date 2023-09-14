package com.sunday.taskapp.data

import androidx.compose.runtime.toMutableStateList

data class Task(
    var index: Int,
    var title: String ,
    var description: String,
    var isChecked: Boolean
)

fun getTasks(): MutableList<Task> {
    var list = (1..4).map { it ->
        Task(
            index = it - 1,
            title = "Título $it",
            description = "Description $it: Ésta es una descripción ligeramente extensa para ver cómo se comporta la app con textos largos",
            isChecked = false
        )
    }
    return list.toMutableStateList()
}
