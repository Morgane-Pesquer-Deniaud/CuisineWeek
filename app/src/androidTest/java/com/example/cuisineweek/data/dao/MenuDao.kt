package com.example.cuisineweek.data.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cuisineweek.data.entity.*

@Dao
interface MenuDao {

    @Query("SELECT * FROM menus_semaine WHERE semaineDu = :semaine LIMIT 1")
    suspend fun getMenuBySemaine(semaine: String): MenuSemaine?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenu(menu: MenuSemaine): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuRecette(menuRecette: MenuRecette)

    @Delete
    suspend fun deleteMenuRecette(menuRecette: MenuRecette)

    // Requête avec jointure : on récupère les recettes d'un jour précis
    @Query("""
        SELECT r.* FROM recettes r
        INNER JOIN menu_recettes mr ON r.id = mr.recetteId
        WHERE mr.menuId = :menuId AND mr.jour = :jour
    """)
    fun getRecettesDuJour(menuId: Int, jour: String): LiveData<List<Recette>>
}