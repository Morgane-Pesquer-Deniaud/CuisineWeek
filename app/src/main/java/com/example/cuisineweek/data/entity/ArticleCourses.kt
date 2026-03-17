package com.example.cuisineweek.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles_courses")
data class ArticleCourses(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nom: String,           // "Spaghetti n°5 Barilla"
    val quantite: String = "", // "500g"
    val codeBarres: String,    // "3017620422003"
    val coche: Boolean = false // article acheté ou pas
)