package com.example.cuisineweek.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cuisineweek.R
import com.example.cuisineweek.viewmodel.RecetteViewModel

class RecettesFragment : Fragment() {

    private val viewModel: RecetteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // On gonfle le layout du fragment
        return inflater.inflate(R.layout.fragment_recettes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecetteAdapter { recette ->
            Toast.makeText(context, "Cliqué : ${recette.nom}", Toast.LENGTH_SHORT).show()
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewRecettes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.toutesLesRecettes.observe(viewLifecycleOwner) { recettes ->
            adapter.updateRecettes(recettes)
        }
    }
}