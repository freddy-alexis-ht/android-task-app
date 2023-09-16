package com.sunday.taskapp.data

import androidx.compose.runtime.toMutableStateList

class TaskProvider: TaskRepository {

    companion object {
        private var temp: List<Task> = (1..4).map { it ->
            Task(
                id = it - 1,
                title = "Título $it",
                description = "Description $it: Ésta es una descripción ligeramente extensa para ver cómo se comporta la app con textos largos",
                isChecked = false
            )
        }
        private var list: MutableList<Task> = temp.toMutableStateList()
    }


    override fun getTasks(): MutableList<Task> {
        return list
    }
    override fun getTaskById(id: Int): Task {
        return list.find { it.id == id }!!
    }
    override fun insertTask(task: Task) {
        list.add(task)
    }
    override fun insertTask(index: Int, task: Task) {
        list.add(index, task)
    }
    override fun updateTask(task: Task) {
        val oldTask = this.getTaskById(task.id)
        val index = list.indexOf(oldTask)
        list.set(index, task)
    }
    override fun deleteTask(task: Task) {
        list.remove(task)
    }
}