package com.example.myapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapp.data.model.SerieEntity
import com.example.myapp.viewmodel.SerieViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.data.local.SerieDatabase
import com.example.myapp.viewmodel.SerieViewModelFactory
import androidx.compose.runtime.livedata.observeAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    navController: NavController, serieId: Int?
) {
    val context = LocalContext.current
    val dao = SerieDatabase.getInstance(context).serieDao()
    val viewModel: SerieViewModel = viewModel(factory = SerieViewModelFactory(dao))

    val series: List<SerieEntity> by viewModel.series.observeAsState(emptyList())

    val existingSerie = serieId?.let { id ->
        series.find { serie -> serie.id == id }
    }


    val nomeState = remember { mutableStateOf("") }
    val diaSemanaState = remember { mutableStateOf("") }
    val imagemState = remember { mutableStateOf("") }

    LaunchedEffect(existingSerie) {
        existingSerie?.let {
            nomeState.value = it.nome
            diaSemanaState.value = it.diaSemana
            imagemState.value = it.imagem ?: ""
        }
    }

    var nome by remember { nomeState }
    var diaSemana by remember { diaSemanaState }
    var imagem by remember { imagemState }


    val diasSemana = listOf("Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo")
    val imagensDisponiveis = listOf("breaking_bad", "the_office", "friends")

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (serieId == null) "Nova Série" else "Editar Série") })
        }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome da Série") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )

            var expandedDia by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = expandedDia, onExpandedChange = { expandedDia = !expandedDia }) {
                TextField(
                    value = diaSemana,
                    onValueChange = {},
                    label = { Text("Dia de Lançamento") },
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedDia, onDismissRequest = { expandedDia = false }) {
                    diasSemana.forEach { dia ->
                        DropdownMenuItem(text = { Text(dia) }, onClick = {
                            diaSemana = dia
                            expandedDia = false
                        })
                    }
                }
            }

            var expandedImagem by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = expandedImagem,
                onExpandedChange = { expandedImagem = !expandedImagem }) {
                TextField(
                    value = imagem,
                    onValueChange = {},
                    label = { Text("Imagem da Série") },
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedImagem, onDismissRequest = { expandedImagem = false }) {
                    imagensDisponiveis.forEach { img ->
                        DropdownMenuItem(text = { Text(img) }, onClick = {
                            imagem = img
                            expandedImagem = false
                        })
                    }
                }
            }

            Button(
                onClick = {
                    if (nome.isNotBlank() && diaSemana.isNotBlank()) {
                        val novaSerie = SerieEntity(
                            id = existingSerie?.id ?: 0,
                            nome = nome,
                            diaSemana = diaSemana,
                            imagem = imagem,
                            favorito = existingSerie?.favorito ?: false
                        )
                        if (serieId == null) viewModel.adicionarSerie(novaSerie)
                        else viewModel.atualizarSerie(novaSerie)

                        navController.popBackStack()
                    }
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar Série")
            }
        }
    }
}