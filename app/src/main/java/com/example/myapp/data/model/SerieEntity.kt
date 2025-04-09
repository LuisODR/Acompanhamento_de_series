package com.example.myapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series")
data class SerieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val diaSemana: String,
    val imagem: String?,
    val favorito: Boolean
)
