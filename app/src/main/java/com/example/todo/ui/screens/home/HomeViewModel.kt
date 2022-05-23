package com.example.todo.ui.screens.home

import androidx.lifecycle.*
import com.example.todo.data.todo.TodoRepository
import com.example.todo.ui.state.TodoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {
    val todos = todoRepository.getTodos().map { list ->
        list.map { item ->
            TodoUiState(
                uid = item.uid,
                text = item.text ?: "",
                completed = item.completed ?: false,
                toggleCompleted = {
                    viewModelScope.launch {
                        todoRepository.todoCompleted(item.uid, !(item.completed ?: false))
                    }
                },
                delete = {
                    viewModelScope.launch {
                        todoRepository.deleteTodo(item)
                    }
                },
                setText = { text ->
                    if (text.isBlank() && item.text == null) {
                        viewModelScope.launch {
                            todoRepository.deleteTodo(item)
                        }
                    } else {
                        viewModelScope.launch {
                            todoRepository.setTodoText(item.uid, text)
                        }
                    }
                }
            )
        }
    }.asLiveData()

    fun addTodo() {
        viewModelScope.launch {
            todoRepository.addTodo("new todo")
        }
    }
}