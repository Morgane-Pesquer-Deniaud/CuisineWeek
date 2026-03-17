package com.example.cuisineweek.data.entity
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "recettes",
    // ForeignKey = lien vers une autre table
    // Si on supprime une catégorie, categorieId devient null (SET_NULL)
    foreignKeys = [ForeignKey(
        entity = Categorie::class,
        parentColumns = ["id"],
        childColumns = ["categorieId"],
        onDelete = ForeignKey.SET_NULL
    )]
)
data class Recette(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nom: String,
    val description: String,
    val tempsPrep: Int,            // en minutes
    val tempsCuisson: Int,         // en minutes
    val nbPersonnes: Int,
    val difficulte: String,        // "Facile", "Moyen", "Difficile"
    val categorieId: Int?,         // ? = peut être null
    val imageUri: String? = null,  // photo optionnelle
    val estPersonnalisee: Boolean = false  // recette de l'utilisateur ?
)