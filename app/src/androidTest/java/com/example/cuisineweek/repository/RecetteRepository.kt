package com.example.cuisineweek.repository


import androidx.lifecycle.LiveData
import com.example.cuisineweek.data.dao.RecetteDao
import com.example.cuisineweek.data.entity.Recette

class RecetteRepository(private val recetteDao: RecetteDao) {
    // On expose les données du DAO vers le ViewModel
    // LiveData = la liste se met à jour automatiquement quand la BDD change
    val toutesLesRecettes: LiveData<List<Recette>> = recetteDao.getAllRecettes()

    // Recherche par mot-clé
    fun rechercherRecettes(query: String): LiveData<List<Recette>> {
        return recetteDao.searchRecettes(query)
    }

    // suspend = fonction asynchrone, s'exécute en arrière-plan
    // sans bloquer l'interface utilisateur
    suspend fun inserer(recette: Recette): Long {
        return recetteDao.insert(recette)
    }

    suspend fun modifier(recette: Recette) {
        recetteDao.update(recette)
    }

    suspend fun supprimer(recette: Recette) {
        recetteDao.delete(recette)
    }
}