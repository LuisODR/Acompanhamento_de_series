package com.example.myapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color

data class NavItem(val route: String, val label: String, val icon: ImageVector)

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavItem("home", "Séries", Icons.Default.Home),
        NavItem("favorites", "Favoritos", Icons.Default.Star),
        NavItem("schedule", "Calendário", Icons.Default.DateRange)
    )

    NavigationBar(
        containerColor = Color.Black,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                selected = selected, onClick = {
                if (currentRoute != item.route) {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }, icon = {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.label,
                    tint = if (selected) Color(0xFFE50914) else Color.White.copy(alpha = 0.6f)
                )
            }, label = {
                Text(
                    text = item.label,
                    color = if (selected) Color(0xFFE50914) else Color.White.copy(alpha = 0.6f)
                )
            }, alwaysShowLabel = true, colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color.Black
            )
            )
        }
    }
}



