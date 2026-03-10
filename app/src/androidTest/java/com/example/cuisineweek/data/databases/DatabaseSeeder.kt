package com.example.cuisineweek.data.databases


import com.example.cuisineweek.data.entity.*

object DatabaseSeeder {
    suspend fun seed(db: AppDatabase) {
        val recetteDao = db.recetteDao()

        // 3 recettes de départ pour tester l'affichage
        recetteDao.insert(Recette(
            nom = "Pâtes Carbonara",
            description = "Un classique italien crémeux et savoureux.",
            tempsPrep = 10, tempsCuisson = 15,
            nbPersonnes = 4, difficulte = "Facile",
            categorieId = null
        ))
        recetteDao.insert(Recette(
            nom = "Salade Niçoise",
            description = "Fraîche et colorée, parfaite en été.",
            tempsPrep = 20, tempsCuisson = 0,
            nbPersonnes = 2, difficulte = "Facile",
            categorieId = null
        ))
        recetteDao.insert(Recette(
            nom = "Poulet Rôti au Citron",
            description = "Fondant dedans, croustillant dehors.",
            tempsPrep = 15, tempsCuisson = 60,
            nbPersonnes = 4, difficulte = "Moyen",
            categorieId = null
        ))
    }
}