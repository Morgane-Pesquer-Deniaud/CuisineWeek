package com.example.cuisineweek.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels          // ← vérifie que cette ligne est là
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cuisineweek.R
import com.example.cuisineweek.viewmodel.CoursesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class CoursesFragment : Fragment() {

    private val viewModel: CoursesViewModel by viewModels()

    private val scanLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            viewModel.ajouterDepuisScan(result.contents)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_courses, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArticleCoursesAdapter(
            onCocheChange = { article -> viewModel.toggleCoche(article) },
            onSupprimer = { article -> viewModel.supprimer(article) }
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCourses)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            adapter.updateArticles(articles)
        }

        view.findViewById<FloatingActionButton>(R.id.btnScanner).setOnClickListener {
            val options = ScanOptions().apply {
                setPrompt("Scannez le code-barre du produit")
                setBeepEnabled(true)
                setOrientationLocked(false)
            }
            scanLauncher.launch(options)
        }
    }
}