package com.example.cuisineweek.data.database


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
            categorieId = 1
        ))

        recetteDao.insert(Recette(
            nom = "Salade Niçoise",
            description = "Fraîche et colorée, parfaite en été.",
            tempsPrep = 20, tempsCuisson = 0,
            nbPersonnes = 2, difficulte = "Facile",
            categorieId = 2
        ))

        recetteDao.insert(Recette(
            nom = "Poulet Rôti au Citron",
            description = "Fondant dedans, croustillant dehors.",
            tempsPrep = 15, tempsCuisson = 60,
            nbPersonnes = 4, difficulte = "Moyen",
            categorieId = 3
        ))

        recetteDao.insert(Recette(
            nom = "Burger Maison",
            description = "Juteux et gourmand avec fromage fondant.",
            tempsPrep = 25, tempsCuisson = 15,
            nbPersonnes = 2, difficulte = "Facile",
            categorieId = 1
        ))

        recetteDao.insert(Recette(
            nom = "Ratatouille",
            description = "Plat végétarien riche en légumes du soleil.",
            tempsPrep = 30, tempsCuisson = 45,
            nbPersonnes = 4, difficulte = "Moyen",
            categorieId = 2
        ))

        recetteDao.insert(Recette(
            nom = "Soupe de Tomates",
            description = "Réconfortante et rapide à préparer.",
            tempsPrep = 10, tempsCuisson = 25,
            nbPersonnes = 3, difficulte = "Facile",
            categorieId = 2
        ))

        recetteDao.insert(Recette(
            nom = "Bœuf Bourguignon",
            description = "Plat traditionnel mijoté au vin rouge.",
            tempsPrep = 30, tempsCuisson = 180,
            nbPersonnes = 6, difficulte = "Difficile",
            categorieId = 3
        ))

        recetteDao.insert(Recette(
            nom = "Omelette Fromage",
            description = "Simple, rapide et efficace.",
            tempsPrep = 5, tempsCuisson = 5,
            nbPersonnes = 1, difficulte = "Facile",
            categorieId = null
        ))

        recetteDao.insert(Recette(
            nom = "Tarte aux Pommes",
            description = "Dessert classique croustillant et sucré.",
            tempsPrep = 20, tempsCuisson = 35,
            nbPersonnes = 6, difficulte = "Moyen",
            categorieId = 4
        ))

        recetteDao.insert(Recette(
            nom = "Sushi Maison",
            description = "Technique mais délicieux.",
            tempsPrep = 50, tempsCuisson = 10,
            nbPersonnes = 2, difficulte = "Difficile",
            categorieId = 5
        ))
        recetteDao.insert(Recette(
            nom = "Pizza Margherita",
            description = "Simple et authentique avec tomate, mozzarella et basilic.",
            tempsPrep = 20, tempsCuisson = 12,
            nbPersonnes = 2, difficulte = "Facile",
            categorieId = 1
        ))

        recetteDao.insert(Recette(
            nom = "Pad Thaï",
            description = "Plat thaïlandais sucré-salé aux nouilles sautées.",
            tempsPrep = 25, tempsCuisson = 10,
            nbPersonnes = 3, difficulte = "Moyen",
            categorieId = 5
        ))

        recetteDao.insert(Recette(
            nom = "Gratin Dauphinois",
            description = "Crémeux et fondant, parfait en accompagnement.",
            tempsPrep = 15, tempsCuisson = 70,
            nbPersonnes = 5, difficulte = "Moyen",
            categorieId = 3
        ))

        recetteDao.insert(Recette(
            nom = "Smoothie Banane-Fraise",
            description = "Boisson fraîche et vitaminée.",
            tempsPrep = 5, tempsCuisson = 0,
            nbPersonnes = 2, difficulte = "Facile",
            categorieId = 6
        ))

        recetteDao.insert(Recette(
            nom = "Tacos Maison",
            description = "Garniture généreuse et personnalisable.",
            tempsPrep = 20, tempsCuisson = 10,
            nbPersonnes = 4, difficulte = "Facile",
            categorieId = 1
        ))

        recetteDao.insert(Recette(
            nom = "Curry de Légumes",
            description = "Parfumé et épicé, idéal végétarien.",
            tempsPrep = 20, tempsCuisson = 30,
            nbPersonnes = 4, difficulte = "Moyen",
            categorieId = 2
        ))

        recetteDao.insert(Recette(
            nom = "Fondant au Chocolat",
            description = "Cœur coulant irrésistible.",
            tempsPrep = 15, tempsCuisson = 10,
            nbPersonnes = 4, difficulte = "Facile",
            categorieId = 4
        ))

        recetteDao.insert(Recette(
            nom = "Lasagnes Bolognaises",
            description = "Plat complet gratiné et savoureux.",
            tempsPrep = 40, tempsCuisson = 50,
            nbPersonnes = 6, difficulte = "Moyen",
            categorieId = 3
        ))

        recetteDao.insert(Recette(
            nom = "Poké Bowl Saumon",
            description = "Frais, équilibré et moderne.",
            tempsPrep = 20, tempsCuisson = 0,
            nbPersonnes = 2, difficulte = "Facile",
            categorieId = 5
        ))

        recetteDao.insert(Recette(
            nom = "Pain Maison",
            description = "Croûte croustillante et mie aérée.",
            tempsPrep = 180, tempsCuisson = 30,
            nbPersonnes = 4, difficulte = "Difficile",
            categorieId = 3
        ))
    }
}