package com.example.myapp.viewmodel

import androidx.lifecycle.*
import com.example.myapp.data.local.SerieDao
import com.example.myapp.data.model.SerieEntity
import kotlinx.coroutines.launch

class SerieViewModel(private val dao: SerieDao) : ViewModel() {

    val series = dao.getAllSeries().asLiveData()
    val favoritas = dao.getFavoritas().asLiveData()

    fun getSeriesByDia(dia: String): LiveData<List<SerieEntity>> {
        return dao.getByDia(dia).asLiveData()
    }

    fun adicionarSerie(serie: SerieEntity) {
        viewModelScope.launch {
            dao.insert(serie)
        }
    }

    fun atualizarSerie(serie: SerieEntity) {
        viewModelScope.launch {
            dao.update(serie)
        }
    }

    fun excluirSerie(serie: SerieEntity) {
        viewModelScope.launch {
            dao.delete(serie)
        }
    }
}
