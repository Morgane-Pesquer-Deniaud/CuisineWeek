package com.example.cuisineweek.data.database


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
        MenuRecette::class,
        ArticleCourses::class  // ← ajoute cette ligne
    ],
    version = 2,
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
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cuisineweek_database"
                )
                .fallbackToDestructiveMigration()  // ← efface et recrée la BDD si la structure change
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                DatabaseSeeder.seed(database)
                            }
                        }
                    }
                })
                .build()
                INSTANCE = instance
                return instance

                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}