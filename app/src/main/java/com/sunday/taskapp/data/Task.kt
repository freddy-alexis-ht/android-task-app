package com.sunday.taskapp.data

data class Task(
    var title: String = "Título",
    var description: String = "Ésta es una descripción ligeramente extensa para ver cómo se comporta la app con textos largos",
    var isDone: Boolean = false
)
