package com.example.cuisineweek.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "recette_ingredients",
    primaryKeys = ["recetteId", "ingredientId"],
    foreignKeys = [
        ForeignKey(
            entity = Recette::class,
            parentColumns = ["id"],
            childColumns = ["recetteId"],
            onDelete = ForeignKey.CASCADE
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
    val quantite: Double,
    val unite: String
)