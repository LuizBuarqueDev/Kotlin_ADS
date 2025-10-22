package com.example.listainterativa.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listainterativa.model.Task

class TaskViewModel : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>(emptyList())

    val tasks: LiveData<List<Task>> get() = _tasks

    fun addTask(title: String) {
        val current = _tasks.value.orEmpty().toMutableList()
        current.add(Task(title = title))
        _tasks.value = current
    }

    fun toggleTask(task: Task) {
        val current = _tasks.value.orEmpty().toMutableList()
        val index = current.indexOfFirst { it.id == task.id }
        if (index != -1) {
            val updated = task.copy(isCompleted = !task.isCompleted)
            current[index] = updated
            _tasks.value = current
        }
    }

    fun deleteTask(task: Task) {
        val current = _tasks.value.orEmpty().toMutableList()
        current.remove(task)
        _tasks.value = current
    }
}

