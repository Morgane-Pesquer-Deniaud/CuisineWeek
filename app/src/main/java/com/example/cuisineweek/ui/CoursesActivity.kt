package com.example.cuisineweek.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cuisineweek.R
import com.example.cuisineweek.viewmodel.CoursesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class CoursesActivity : AppCompatActivity() {

    private val viewModel: CoursesViewModel by viewModels()

    // Lance le scanner et récupère le résultat
    private val scanLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            // result.contents = le code-barre scanné
            viewModel.ajouterDepuisScan(result.contents)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)

        val adapter = ArticleCoursesAdapter(
            onCocheChange = { article -> viewModel.toggleCoche(article) },
            onSupprimer = { article -> viewModel.supprimer(article) }
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCourses)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.articles.observe(this) { articles ->
            adapter.updateArticles(articles)
        }

        // Bouton scan
        findViewById<FloatingActionButton>(R.id.btnScanner).setOnClickListener {
            val options = ScanOptions().apply {
                setPrompt("Scannez le code-barre du produit")
                setBeepEnabled(true)
                setOrientationLocked(false)
            }
            scanLauncher.launch(options)
        }
    }
}