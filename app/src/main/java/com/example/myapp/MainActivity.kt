package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.myapp.ui.components.BottomNavigationBar
import androidx.navigation.compose.*
import com.example.myapp.ui.screens.*
import androidx.compose.ui.Modifier
import com.example.myapp.ui.theme.myapptrackertheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myapptrackertheme {
                val navController = rememberNavController()

                Scaffold(

                    bottomBar = { BottomNavigationBar(navController) }) {

                        padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        Modifier.padding(padding)
                    ) {
                        composable("home") { HomeScreen(navController) }
                        composable("favorites") { FavoritesScreen(navController) }
                        composable("schedule") { ScheduleScreen(navController) }
                        composable("addEdit/{serieId}") { backStackEntry ->
                            val serieId =
                                backStackEntry.arguments?.getString("serieId")?.toIntOrNull()
                            AddEditScreen(navController, serieId)
                        }
                        composable("detail/{serieId}") { backStackEntry ->
                            val serieId =
                                backStackEntry.arguments?.getString("serieId")?.toIntOrNull()
                            DetailScreen(navController, serieId)
                        }
                    }
                }
            }
        }
    }
}


