package com.example.cuisineweek.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

// Cette table répond à la question :
// "Quelle quantité de quel ingrédient pour quelle recette ?"
// Elle n'a pas d'ID propre - la clé primaire est la COMBINAISON (recetteId + ingredientId)
@Entity(
    tableName = "recette_ingredients",
    primaryKeys = ["recetteId", "ingredientId"],
    foreignKeys = [
        ForeignKey(
            entity = Recette::class,
            parentColumns = ["id"],
            childColumns = ["recetteId"],
            onDelete = ForeignKey.CASCADE  // si recette supprimée → ses ingrédients aussi
        ),
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["id"],
            childColumns = ["ingredientId"]
        )
    ]
)
data class RecetteIngredient(
    val recetteId: Int,
    val ingredientId: Int,
    val quantite: Double,   // ex: 400.0
    val unite: String       // ex: "g" (peut différer de l'unité par défaut)
)