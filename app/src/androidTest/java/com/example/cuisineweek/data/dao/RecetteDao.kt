package com.example.cuisineweek.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cuisineweek.data.entity.Recette

@Dao
interface RecetteDao {

    // LiveData = la liste se met à jour automatiquement dans l'interface
    // quand les données changent en BDD. Très pratique !
    @Query("SELECT * FROM recettes ORDER BY nom ASC")
    fun getAllRecettes(): LiveData<List<Recette>>

    // :query = paramètre qu'on passera à la fonction
    // LIKE '%...%' = contient ce mot (recherche partielle)
    @Query("SELECT * FROM recettes WHERE nom LIKE '%' || :query || '%'")
    fun searchRecettes(query: String): LiveData<List<Recette>>

    @Query("SELECT * FROM recettes WHERE id = :id")
    suspend fun getRecetteById(id: Int): Recette?
    // suspend = fonction asynchrone (ne bloque pas l'interface)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recette: Recette): Long  // retourne l'ID créé

    @Update
    suspend fun update(recette: Recette)

    @Delete
    suspend fun delete(recette: Recette)
}