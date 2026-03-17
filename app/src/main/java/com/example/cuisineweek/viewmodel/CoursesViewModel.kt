package com.example.cuisineweek.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cuisineweek.data.database.AppDatabase
import com.example.cuisineweek.data.entity.ArticleCourses
import com.example.cuisineweek.data.model.RetrofitClient
import com.example.cuisineweek.data.dao.ArticleCoursesDao
import kotlinx.coroutines.launch

class CoursesViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).articleCoursesDao()
    val articles: LiveData<List<ArticleCourses>> = dao.getAllArticles()

    // Appelé après un scan réussi
    fun ajouterDepuisScan(barcode: String) = viewModelScope.launch {
        try {
            // 1. On appelle l'API avec le code-barre
            val response = RetrofitClient.api.getProduct(barcode)

            // 2. Si le produit est trouvé (status = 1)
            if (response.status == 1 && response.product != null) {
                val produit = response.product

                // 3. On crée un article et on l'insère en BDD
                val article = ArticleCourses(
                    nom = produit.nom ?: "Produit inconnu",
                    quantite = produit.quantite ?: "",
                    codeBarres = barcode
                )
                dao.insert(article)
            }
        } catch (e: Exception) {
            // En cas d'erreur réseau on ne crashe pas
            e.printStackTrace()
        }
    }

    fun toggleCoche(article: ArticleCourses) = viewModelScope.launch {
        dao.update(article.copy(coche = !article.coche))
    }

    fun supprimer(article: ArticleCourses) = viewModelScope.launch {
        dao.delete(article)
    }

    fun toutEffacer() = viewModelScope.launch {
        dao.deleteAll()
    }
}