package com.example.todo.data.modules

import android.app.Application
import androidx.room.Room
import com.example.todo.data.AppDatabase
import com.example.todo.data.todo.TodoDao
import com.example.todo.data.todo.sources.TodoLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "todo_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: AppDatabase): TodoDao {
        return database.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoLocalDataSource(todoDao: TodoDao): TodoLocalDataSource {
        return TodoLocalDataSource(todoDao = todoDao)
    }
}