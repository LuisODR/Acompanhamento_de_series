package com.example.myapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapp.viewmodel.SerieViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.data.local.SerieDatabase
import com.example.myapp.data.model.SerieEntity
import com.example.myapp.viewmodel.SerieViewModelFactory
import com.example.myapp.R
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController, serieId: Int?
) {
    val context = LocalContext.current
    val dao = SerieDatabase.getInstance(context).serieDao()
    val viewModel: SerieViewModel = viewModel(factory = SerieViewModelFactory(dao))

    val series: List<SerieEntity> by viewModel.series.observeAsState(emptyList())
    val serie = serieId?.let { id -> series.find { it.id == id } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalhes da Série: ${serie?.nome ?: "Carregando..."}") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                })
        }) { padding ->
        serie?.let {
            val imagemResId = when (it.imagem) {
                "breaking_bad" -> R.drawable.breakingbad
                "the_office" -> R.drawable.the_office
                "friends" -> R.drawable.friends
                else -> R.drawable.placeholder
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = imagemResId),
                    contentDescription = "Imagem da série",
                    modifier = Modifier
                        .size(200.dp)
                        .aspectRatio(1f)
                )

                Text(
                    text = it.nome,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Lançamento: ${it.diaSemana}", style = MaterialTheme.typography.bodyLarge
                )
            }
        } ?: run {
            Text(
                text = "Série não encontrada.", modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            )
        }
    }
}