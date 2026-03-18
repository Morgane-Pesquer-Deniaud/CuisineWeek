package com.example.cuisineweek.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cuisineweek.R
import com.example.cuisineweek.data.entity.Recette
import com.example.cuisineweek.viewmodel.RecetteViewModel

class SelectionRecetteDialog(
    private val typeRepas: String,
    private val onRecetteSelectionnee: (Recette) -> Unit
) : DialogFragment() {

    private val viewModel: RecetteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_selection_recette, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Titre du dialog
        view.findViewById<TextView>(R.id.tvTitreDialog).text =
            "Choisir une recette pour $typeRepas"

        val adapter = RecetteAdapter { recette ->
            onRecetteSelectionnee(recette)
            dismiss() // ferme le dialog
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewDialog)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.toutesLesRecettes.observe(viewLifecycleOwner) { recettes ->
            adapter.updateRecettes(recettes)
        }
    }

    override fun onStart() {
        super.onStart()
        // Taille du dialog : 90% de la largeur, 70% de la hauteur
        dialog?.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.9).toInt(),
            (resources.displayMetrics.heightPixels * 0.7).toInt()
        )
    }
}