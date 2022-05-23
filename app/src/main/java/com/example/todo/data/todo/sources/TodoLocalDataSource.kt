package com.example.todo.data.todo.sources

import com.example.todo.data.todo.Todo
import com.example.todo.data.todo.TodoDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoLocalDataSource @Inject constructor (
    private val todoDao: TodoDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getTodos(): Flow<List<Todo>> = todoDao.getTodos()

    suspend fun todoCompleted(uid: Int, completed: Boolean) {
        withContext(ioDispatcher) {
            todoDao.setCompleted(uid, completed)
        }
    }

    suspend fun setTodoText(uid: Int, text: String) {
        withContext(ioDispatcher) {
            todoDao.setText(uid, text)
        }
    }

    suspend fun addTodo(text: String?) {
        withContext(ioDispatcher) {
            todoDao.insert(Todo(text = text, completed = false))
        }
    }

    suspend fun deleteTodo(todo: Todo) {
        withContext(ioDispatcher) {
            todoDao.delete(todo)
        }
    }
}