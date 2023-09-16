package com.sunday.taskapp.data

interface TaskRepository {
    fun getTasks(): MutableList<Task>
    fun getTaskById(id: Int): Task
    fun insertTask(task: Task)
    fun insertTask(index: Int, task: Task)
    fun updateTask(task: Task)
    fun deleteTask(task: Task)
}