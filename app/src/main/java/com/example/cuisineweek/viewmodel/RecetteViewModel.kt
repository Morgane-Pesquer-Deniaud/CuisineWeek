package com.example.cuisineweek.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cuisineweek.data.database.AppDatabase
import com.example.cuisineweek.data.entity.Recette
import com.example.cuisineweek.repository.RecetteRepository
import kotlinx.coroutines.launch

// AndroidViewModel = comme ViewModel mais avec accès au contexte Android
// (nécessaire pour accéder à la base de données)
class RecetteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecetteRepository
    val toutesLesRecettes: LiveData<List<Recette>>

    init {
        // init = bloc exécuté automatiquement à la création du ViewModel
        val recetteDao = AppDatabase.getDatabase(application).recetteDao()
        repository = RecetteRepository(recetteDao)
        toutesLesRecettes = repository.toutesLesRecettes
    }

    fun rechercherRecettes(query: String): LiveData<List<Recette>> {
        return repository.rechercherRecettes(query)
    }

    // viewModelScope = coroutine qui s'arrête automatiquement
    // quand le ViewModel est détruit
    fun inserer(recette: Recette) = viewModelScope.launch {
        repository.inserer(recette)
    }

    fun supprimer(recette: Recette) = viewModelScope.launch {
        repository.supprimer(recette)
    }
}