package com.sunday.taskapp

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sunday.taskapp.data.Task
import com.sunday.taskapp.util.JsonConverter
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun prueba() {
        val json = "{\"id\":2,\"title\":\"n1\",\"description\":\"d1\",\"isChecked\":false}"
        val task: Task? = this.fromJsonToTask(json)
        val lista = mutableListOf(task!!, task!!, task!!)
        println(this.fromTaskListToJson(lista))
        // En un string de una línea
        // [{"id":2,"title":"n1","description":"d1","isChecked":false},
        // {"id":2,"title":"n1","description":"d1","isChecked":false},
        // {"id":2,"title":"n1","description":"d1","isChecked":false}]
        val l: List<Task>? = fromJsonToTaskList(fromTaskListToJson(lista))
        if(l != null) {
            l.forEach { println(it.id) } // 2 2 2
        }
    }

    val gson = Gson()

    fun fromTaskListToJson(itemList: MutableList<Task>): String {
        val taskListToJson = gson.toJson(itemList)
        return taskListToJson
    }
    fun fromJsonToTaskList(json: String): MutableList<Task>? {
        val taskType = object : TypeToken<MutableList<Task>>() {}.type
        var jsonToTaskList = gson.fromJson<MutableList<Task>>(json, taskType)
        return jsonToTaskList
    }

    fun fromTaskToJson(itemList: Task): String {
        val taskToJson = gson.toJson(itemList)
        return taskToJson
    }
    fun fromJsonToTask(json: String): Task? {
        val itemType = object : TypeToken<Task>() {}.type
        return gson.fromJson<Task>(json, itemType) as Task
    }

}