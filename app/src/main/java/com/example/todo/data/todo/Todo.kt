package com.example.todo.data.todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val text: String?,
    val completed: Boolean?
)

