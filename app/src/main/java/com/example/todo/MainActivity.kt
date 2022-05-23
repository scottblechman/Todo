package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo.ui.theme.TodoTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController
import com.example.todo.ui.screens.home.HomeScreen
import com.example.todo.ui.screens.home.HomeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoTheme {
                TodoApp()
            }
        }
    }
}

@Composable
private fun TodoApp() {
    val navController = rememberNavController()

    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    val viewModel: HomeViewModel = hiltViewModel()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    when (currentRoute.value?.destination?.route) {
                        "home" -> { viewModel.addTodo() }
                    }
                }
            ) {
                Icon(
                    imageVector = Filled.Add,
                    contentDescription = "Add a new to-do item",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(viewModel)
            }
        }
    }
}