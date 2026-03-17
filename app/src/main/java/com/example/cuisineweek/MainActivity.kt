package com.example.cuisineweek

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cuisineweek.R.*
import com.example.cuisineweek.ui.CoursesActivity
import com.example.cuisineweek.ui.RecetteAdapter
import com.example.cuisineweek.viewmodel.RecetteViewModel

private val Unit.btnVoirCourses: Int

class MainActivity : AppCompatActivity() {

    // viewModels() = crée ou récupère le ViewModel
    // (survit aux rotations d'écran)
    private val viewModel: RecetteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
// Ajoute un bouton dans activity_main.xml
// puis dans MainActivity :
        findViewById<android.widget.Button>(id.btnVoirCourses).setOnClickListener {
            startActivity(
                android.content.Intent(this, CoursesActivity::class.java)
            )
        }
        // 1. On crée l'Adapter
        val adapter = RecetteAdapter { recette ->
            // Ce code s'exécute quand on clique sur une recette
            Toast.makeText(this, "Cliqué : ${recette.nom}", Toast.LENGTH_SHORT).show()
        }

        // 2. On configure le RecyclerView
        val recyclerView = findViewById<RecyclerView>(id.recyclerViewRecettes)
        recyclerView.adapter = adapter
        // LinearLayoutManager = liste verticale simple (une carte par ligne)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 3. On observe les données
        // observe = dès que la BDD change, ce bloc s'exécute automatiquement
        viewModel.toutesLesRecettes.observe(this) { recettes ->
            adapter.updateRecettes(recettes)
        }
    }
}