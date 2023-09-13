package com.sunday.taskapp.data

data class Task(
    var index: Int = 0,
    var title: String = "Título",
    var description: String = "Ésta es una descripción ligeramente extensa para ver cómo se comporta la app con textos largos",
    var isDone: Boolean = false
)

fun getTasks() = (1..4).map { it ->
    Task(
        index = it-1,
        title = "Título $it",
        description = "Description $it: Ésta es una descripción ligeramente extensa para ver cómo se comporta la app con textos largos",
        isDone = false
    )
}