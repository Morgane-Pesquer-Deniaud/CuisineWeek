package com.example.cuisineweek.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class Ingredient(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nom: String,               // ex: "Spaghetti"
    val uniteDefaut: String,       // ex: "g", "ml", "pièce"
    val categorieCourses: String   // ex: "Épicerie", "Légumes", "Produits frais"
)