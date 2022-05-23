package com.example.todo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.todo.data.AppDatabase
import com.example.todo.data.todo.Todo
import com.example.todo.data.todo.TodoDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class TodoDaoTest {
    private lateinit var todoDao: TodoDao
    private lateinit var db: AppDatabase
    private lateinit var todo: Todo

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        todoDao = db.todoDao()
        todo = Todo(1, "test todo", false)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun addAndDeleteTodo() = runBlocking {
        assertEquals(0, todoDao.getTodos().first().size)
        todoDao.insert(todo)
        assertEquals(1, todoDao.getTodos().first().size)
        val t = todoDao.getTodos().first().first()
        assertEquals(todo.text, t.text)
        assertEquals(todo.completed, t.completed)
        todoDao.delete(todo)
        assertEquals(0, todoDao.getTodos().first().size)
    }

    @Test
    @Throws(Exception::class)
    fun setTodoCompleted() = runBlocking {
        todoDao.insert(todo)
        todoDao.setCompleted(todo.uid, true)
        var t = todoDao.getTodos().first().first()
        assertEquals(true, t.completed)
        todoDao.setCompleted(todo.uid, false)
        t = todoDao.getTodos().first().first()
        assertEquals(false, t.completed)
    }

    @Test
    @Throws(Exception::class)
    fun setTodoText() = runBlocking {
        todoDao.insert(todo)
        todoDao.setText(todo.uid, "todo text")
        var t = todoDao.getTodos().first().first()
        assertEquals("todo text", t.text)
        todoDao.setText(todo.uid, "new value")
        t = todoDao.getTodos().first().first()
        assertEquals("new value", t.text)
    }
}