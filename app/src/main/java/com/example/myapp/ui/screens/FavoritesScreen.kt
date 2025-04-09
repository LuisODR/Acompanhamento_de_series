package com.example.myapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController) {
    val context = LocalContext.current
    val dao = SerieDatabase.getInstance(context).serieDao()
    val viewModel: SerieViewModel = viewModel(factory = SerieViewModelFactory(dao))

    val favoritas: List<SerieEntity> by viewModel.favoritas.observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Séries Favoritas") })
        }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(favoritas) { serie ->
                SerieItem(serie, navController, viewModel)
            }
        }
    }
}

