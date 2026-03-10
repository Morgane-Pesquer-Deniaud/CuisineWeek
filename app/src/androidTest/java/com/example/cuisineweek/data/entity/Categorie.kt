package com.example.cuisineweek.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity dit à Room "cette classe est une table"
// tableName = le nom de la table dans la BDD
@Entity(tableName = "categories")
data class Categorie(

    // @PrimaryKey = clé primaire (identifiant unique)
    // autoGenerate = Room crée l'ID automatiquement (1, 2, 3...)
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nom: String,    // ex: "Plat principal"
    val icone: String   // ex: "🍽️"
)