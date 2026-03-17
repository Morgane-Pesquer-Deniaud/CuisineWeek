package com.example.cuisineweek.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cuisineweek.data.dao.ArticleCoursesDao
import com.example.cuisineweek.data.dao.MenuDao
import com.example.cuisineweek.data.dao.RecetteDao
import com.example.cuisineweek.data.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Categorie::class,
        Recette::class,
        Ingredient::class,
        RecetteIngredient::class,
        MenuSemaine::class,
        MenuRecette::class,
        ArticleCourses::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recetteDao(): RecetteDao
    abstract fun menuDao(): MenuDao
    abstract fun articleCoursesDao(): ArticleCoursesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {

                // On crée la BDD d'abord
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cuisineweek_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                // On l'assigne AVANT le seeder
                INSTANCE = instance

                // Puis on lance le seeder si la BDD est vide
                CoroutineScope(Dispatchers.IO).launch {
                    val count = instance.recetteDao().getCount()
                    android.util.Log.d("SEEDER", "Nombre de recettes en BDD : $count")
                    if (count == 0) {
                        android.util.Log.d("SEEDER", "🌱 Seeder lancé !")
                        DatabaseSeeder.seed(instance)
                    }
                }

                instance
            }
        }
    }
}