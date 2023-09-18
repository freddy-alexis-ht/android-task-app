package com.sunday.taskapp.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sunday.taskapp.data.Task

object JsonConverter {
    // json structure
    // "{\"id\":2,\"title\":\"n1\",\"description\":\"d1\",\"isChecked\":false}"
    private val gson = Gson()

    fun fromTaskToJson(itemList: Task): String {
        return gson.toJson(itemList)
    }
    fun fromJsonToTask(json: String): Task? {
        val itemType = object : TypeToken<Task>() {}.type
        return gson.fromJson<Task>(json, itemType) as Task
    }

    fun fromTaskListToJson(itemList: MutableList<Task>): String {
        return gson.toJson(itemList)
    }
    fun fromJsonToTaskList(json: String): MutableList<Task>? {
        val taskType = object : TypeToken<MutableList<Task>>() {}.type
        return gson.fromJson(json, taskType)
    }

}