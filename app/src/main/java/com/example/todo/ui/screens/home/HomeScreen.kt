package com.example.todo.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import com.example.todo.ui.screens.home.components.TodoList
import com.example.todo.ui.state.TodoUiState

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val todos: List<TodoUiState>? by viewModel.todos.observeAsState(null)

    Home(
        todos = todos
    )
}

@Composable
private fun Home(todos: List<TodoUiState>?) {
    if (todos == null) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else {
        TodoList(todos)
    }
}