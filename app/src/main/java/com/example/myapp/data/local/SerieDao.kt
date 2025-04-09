package com.example.myapp.data.local

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Query
import com.example.myapp.data.model.SerieEntity
import androidx.room.*

@Dao
interface SerieDao {

    @Query("SELECT * FROM series")
    fun getAllSeries(): Flow<List<SerieEntity>>

    @Query("SELECT * FROM series WHERE nome LIKE '%' || :query || '%'")
    fun buscarPorNome(query: String): Flow<List<SerieEntity>>


    @Query("SELECT * FROM series WHERE favorito = 1")
    fun getFavoritas(): Flow<List<SerieEntity>>

    @Query("SELECT * FROM series WHERE diaSemana = :dia")
    fun getByDia(dia: String): Flow<List<SerieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(serie: SerieEntity)

    @Update
    suspend fun update(serie: SerieEntity)

    @Delete
    suspend fun delete(serie: SerieEntity)
}
