package com.example.myapp.ui.components


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapp.data.model.SerieEntity
import com.example.myapp.viewmodel.SerieViewModel
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.res.painterResource
import com.example.myapp.R

@Composable
fun SerieItem(
    serie: SerieEntity, navController: NavController, viewModel: SerieViewModel
) {
    val imagemResId = when (serie.imagem) {
        "breaking_bad" -> R.drawable.breakingbad
        "the_office" -> R.drawable.the_office
        "friends" -> R.drawable.friends
        else -> R.drawable.placeholder
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("detail/${serie.id}") }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = imagemResId),
            contentDescription = "Imagem da série",
            modifier = Modifier
                .size(80.dp)
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = serie.nome, fontWeight = FontWeight.Bold)
            Text(text = "Lança: ${serie.diaSemana}")
        }

        IconButton(onClick = {
            viewModel.atualizarSerie(serie.copy(favorito = !serie.favorito))
        }) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Favorito",
                tint = if (serie.favorito) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.4f
                )
            )
        }

        IconButton(onClick = {
            navController.navigate("addEdit/${serie.id}")
        }) {
            Icon(
                imageVector = Icons.Default.Edit, contentDescription = "Editar Série"
            )
        }


        IconButton(onClick = {
            viewModel.excluirSerie(serie)
        }) {
            Icon(
                imageVector = Icons.Default.Delete, contentDescription = "Excluir"
            )
        }
    }
}