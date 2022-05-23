package com.example.todo.data.modules

import com.example.todo.data.todo.TodoRepository
import com.example.todo.data.todo.sources.TodoLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideTodoRepository(todoLocalDataSource: TodoLocalDataSource): TodoRepository {
        return TodoRepository(todoLocalDataSource = todoLocalDataSource)
    }
}