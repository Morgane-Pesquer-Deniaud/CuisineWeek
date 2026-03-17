package com.example.cuisineweek.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "menu_recettes",
    primaryKeys = ["menuId", "recetteId", "jour", "typeRepas"],
    foreignKeys = [
        ForeignKey(
            entity = MenuSemaine::class,
            parentColumns = ["id"],
            childColumns = ["menuId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Recette::class,
            parentColumns = ["id"],
            childColumns = ["recetteId"]
        )
    ]
)
data class MenuRecette(
    val menuId: Int,
    val recetteId: Int,
    val jour: String,
    val typeRepas: String
)