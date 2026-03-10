package com.example.cuisineweek.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Représente le planning d'une semaine donnée
@Entity(tableName = "menus_semaine")
data class MenuSemaine(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val semaineDu: String,     // ex: "2026-03-03" (date du lundi)
    val nbPersonnes: Int = 4   // valeur par défaut : 4 personnes
)