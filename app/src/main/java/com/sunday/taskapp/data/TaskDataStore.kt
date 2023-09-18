package com.sunday.taskapp.data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sunday.taskapp.util.JsonConverter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val Context.dataStore by preferencesDataStore("task_preferences")

class TaskDataStore(context: Context) : TaskRepository {

    private val dataStore = context.dataStore

    fun getTasks2(): MutableList<Task>? {
        this.clearDataStore()
        Log.i("MyTag", "getTasks .. inicio")
        var list: MutableList<Task>? = mutableListOf()
        val job = GlobalScope.launch {
            save("2", "{\"id\":2,\"title\":\"n1\",\"description\":\"d1\",\"isChecked\":false}")
            Log.i("MyTag", "Inside coroutine 1")
            save("3", "{\"id\":3,\"title\":\"n2\",\"description\":\"d2\",\"isChecked\":false}")
            Log.i("MyTag", "Inside coroutine 2")
            readAllKeys()?.forEach {
                val valueByKey: String = getValueByKey(it) as String
                Log.i("MyTag", "valueByKey: $valueByKey")
                val task: Task? = JsonConverter.fromJsonToTask(valueByKey)
                Log.i("MyTag", "task: ${task?.title}")
                list?.add(task!!)
//                Log.i("MyTag", "Size: ${list?.size}")

            }
        }
        runBlocking {
            job.join()
//            Log.i("MyTag", "Final size: ${list?.size}")
            return@runBlocking list
        }
        return list
    }

    override fun getTasks(): MutableList<Task> {
        TODO("Not yet implemented")
    }

    override fun getTaskById(id: Int): Task {
        TODO("Not yet implemented")
    }

    override fun insertTask(task: Task) {
        GlobalScope.launch {
            save("code1", "texto1")
            save("code2", "texto2")
            save("code3", "texto3")
        }
    }

    override fun insertTask(index: Int, task: Task) {
        TODO("Not yet implemented")
    }

    override fun updateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override fun deleteTask(task: Task) {
        TODO("Not yet implemented")
    }

    suspend fun save(key: String, value: String) {
        val dataStoreKey: Preferences.Key<String> = stringPreferencesKey(key)
        dataStore.edit { preferences -> // it: MutablePreferences
            preferences[dataStoreKey] = value
        }
    }

    suspend fun read(key: String): String? {
        val dataStoreKey: Preferences.Key<String> = stringPreferencesKey(key)
        var preferences: Preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun readAllKeys(): Set<Preferences.Key<*>>? {
        val keys = dataStore.data
            .map {
                it.asMap().keys
            }
        return keys.firstOrNull()
    }

    suspend fun getValueByKey(key: Preferences.Key<*>): Any? {
        val value = dataStore.data
            .map {
                it[key]
            }
        return value.firstOrNull()
    }

    fun clearDataStore() {
        GlobalScope.launch {
            dataStore.edit {
                it.clear()
            }
        }
    }

}