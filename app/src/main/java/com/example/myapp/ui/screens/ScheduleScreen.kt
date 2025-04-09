package com.example.myapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.data.local.SerieDatabase
import com.example.myapp.ui.components.SerieItem
import com.example.myapp.viewmodel.SerieViewModel
import com.example.myapp.viewmodel.SerieViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(navController: NavController) {
    val context = LocalContext.current
    val dao = SerieDatabase.getInstance(context).serieDao()
    val viewModel: SerieViewModel = viewModel(factory = SerieViewModelFactory(dao))

    val dias = listOf("Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calendário de Lançamentos") })
        }) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(dias) { dia ->
                val seriesDoDia by viewModel.getSeriesByDia(dia).observeAsState(emptyList())

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = dia,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )

                    if (seriesDoDia.isEmpty()) {
                        Text(
                            text = "Não há episódios lançados neste dia.",
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                        )
                    } else {
                        seriesDoDia.forEach { serie ->
                            SerieItem(
                                serie = serie, navController = navController, viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
