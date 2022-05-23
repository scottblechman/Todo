package com.example.todo.data.todo

import com.example.todo.data.todo.sources.TodoLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoLocalDataSource: TodoLocalDataSource
) {
    fun getTodos(): Flow<List<Todo>> =
        todoLocalDataSource.getTodos()

    suspend fun todoCompleted(uid: Int, completed: Boolean) =
        todoLocalDataSource.todoCompleted(uid, completed)

    suspend fun setTodoText(uid: Int, text: String) =
        todoLocalDataSource.setTodoText(uid, text)

    suspend fun addTodo(text: String?) =
        todoLocalDataSource.addTodo(text)

    suspend fun deleteTodo(todo: Todo) =
        todoLocalDataSource.deleteTodo(todo)
}