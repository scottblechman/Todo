package com.example.todo.data.todo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY uid ASC")
    fun getTodos(): Flow<List<Todo>>

    @Query("UPDATE todos SET completed = :completed WHERE uid = :uid")
    suspend fun setCompleted(uid: Int, completed: Boolean)

    @Query("UPDATE todos SET text = :text WHERE uid = :uid")
    suspend fun setText(uid: Int, text: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg todos: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}
