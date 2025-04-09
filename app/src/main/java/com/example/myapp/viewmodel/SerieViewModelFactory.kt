package com.example.myapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.data.local.SerieDao

class SerieViewModelFactory(private val dao: SerieDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SerieViewModel::class.java)) {
            return SerieViewModel(dao) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}
