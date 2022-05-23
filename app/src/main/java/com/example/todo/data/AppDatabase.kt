package com.example.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.todo.Todo
import com.example.todo.data.todo.TodoDao

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}