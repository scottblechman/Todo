package com.example.todo.ui.state

data class TodoUiState(
    val uid: Int,
    val text: String,
    val completed: Boolean = false,
    val toggleCompleted: () -> Unit,
    val delete: () -> Unit,
    val setText: (String) -> Unit
)
