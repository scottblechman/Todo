package com.example.todo.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.ui.state.TodoUiState
import com.example.todo.ui.theme.TodoTheme

@Composable
fun TodoListItem(todo: TodoUiState) {
    Card(
        elevation = 10.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = {
                    todo.toggleCompleted()
                }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = todo.text
                )
            }

            IconButton(
                onClick = {
                    todo.delete()
                }
            ) {
                Icon(
                    imageVector = Filled.Delete,
                    contentDescription = "Delete to-do: ${todo.text}",
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TodoListItemPreview() {
    TodoTheme {
        TodoListItem(todo = TodoUiState(0, "todo", false, {}, {}) {})
    }
}