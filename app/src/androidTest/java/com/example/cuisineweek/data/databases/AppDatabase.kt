package com.example.cuisineweek.data.databases


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cuisineweek.data.dao.MenuDao
import com.example.cuisineweek.data.dao.RecetteDao
import com.example.cuisineweek.data.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// @Database liste toutes les tables et la version du schéma
@Database(
    entities = [
        Categorie::class,
        Recette::class,
        Ingredient::class,
        RecetteIngredient::class,
        MenuSemaine::class,
        MenuRecette::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // Room génère l'implémentation de ces DAOs automatiquement
    abstract fun recetteDao(): RecetteDao
    abstract fun menuDao(): MenuDao

    companion object {
        // @Volatile = toutes les threads voient la même valeur
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Singleton : une seule instance de la BDD dans toute l'appli
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cuisineweek_database"  // nom du fichier BDD sur le téléphone
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            // appelé UNE SEULE FOIS, au tout premier lancement
                            INSTANCE?.let { database ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    DatabaseSeeder.seed(database)
                                }
                            }
                        }
                    })
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}