package com.example.myapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.data.local.SerieDatabase
import com.example.myapp.data.model.SerieEntity
import com.example.myapp.ui.components.SerieItem
import com.example.myapp.viewmodel.SerieViewModel
import com.example.myapp.viewmodel.SerieViewModelFactory
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import com.example.myapp.ui.components.TopBarComBusca


@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val dao = SerieDatabase.getInstance(context).serieDao()
    val viewModel: SerieViewModel = viewModel(factory = SerieViewModelFactory(dao))

    val allSeries: List<SerieEntity> by viewModel.series.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }

    val filteredSeries = allSeries.filter { serie ->
        serie.nome.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(topBar = {
        TopBarComBusca(
            searchQuery = searchQuery, onQueryChange = { searchQuery = it })
    }, floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate("addEdit/null")
        }) {
            Icon(Icons.Default.Add, contentDescription = "Adicionar Série")
        }
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (filteredSeries.isEmpty()) {
                Text(
                    text = "Nenhuma série encontrada.", modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyColumn {
                    items(filteredSeries) { serie ->
                        SerieItem(
                            serie = serie, navController = navController, viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}