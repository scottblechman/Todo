package com.example.todo.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.ui.state.TodoUiState
import com.example.todo.ui.theme.TodoTheme

@Composable
fun TodoList(todos: List<TodoUiState>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = todos) { todo ->
            TodoListItem(todo = todo)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TodoListPreview() {
    val todos = List(10) {
        TodoUiState(it, "todo $it", it % 2 == 0, {}, {}) {}
    }
    TodoTheme {
        TodoList(todos = todos)
    }
}