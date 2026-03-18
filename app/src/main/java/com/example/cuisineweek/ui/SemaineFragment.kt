package com.example.cuisineweek.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cuisineweek.R
import com.example.cuisineweek.viewmodel.MenuViewModel
import java.time.LocalDate

class SemaineFragment : Fragment() {

    private val viewModel: MenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_semaine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Références aux vues
        val tvJour = view.findViewById<TextView>(R.id.tvJourActuel)
        val btnPrecedent = view.findViewById<Button>(R.id.btnJourPrecedent)
        val btnSuivant = view.findViewById<Button>(R.id.btnJourSuivant)

        // Midi
        val tvMidi = view.findViewById<TextView>(R.id.tvRecetteMidi)
        val layoutBtnsMidi = view.findViewById<LinearLayout>(R.id.layoutBtnsMidi)
        val btnChangerMidi = view.findViewById<Button>(R.id.btnChangerMidi)
        val btnSupprimerMidi = view.findViewById<Button>(R.id.btnSupprimerMidi)

        // Soir
        val tvSoir = view.findViewById<TextView>(R.id.tvRecetteSoir)
        val layoutBtnsSoir = view.findViewById<LinearLayout>(R.id.layoutBtnsSoir)
        val btnChangerSoir = view.findViewById<Button>(R.id.btnChangerSoir)
        val btnSupprimerSoir = view.findViewById<Button>(R.id.btnSupprimerSoir)

        // Matin
        val tvMatin = view.findViewById<TextView>(R.id.tvRecetteMatin)
        val layoutBtnsMatin = view.findViewById<LinearLayout>(R.id.layoutBtnsMatin)
        val btnChangerMatin = view.findViewById<Button>(R.id.btnChangerMatin)
        val btnSupprimerMatin = view.findViewById<Button>(R.id.btnSupprimerMatin)

        // Navigation entre les jours
        btnPrecedent.setOnClickListener { viewModel.jourPrecedent() }
        btnSuivant.setOnClickListener { viewModel.jourSuivant() }

        // Observe le jour actuel → met à jour le titre
        viewModel.jourActuel.observe(viewLifecycleOwner) { date ->
            tvJour.text = viewModel.getDateFormatee(date)
        }

        // Observe la recette du midi
        viewModel.recetteMidi.observe(viewLifecycleOwner) { recette ->
            if (recette != null) {
                tvMidi.text = recette.nom
                layoutBtnsMidi.visibility = View.VISIBLE
            } else {
                tvMidi.text = "+ Ajouter un repas"
                layoutBtnsMidi.visibility = View.GONE
            }
        }

        // Observe la recette du soir
        viewModel.recetteSoir.observe(viewLifecycleOwner) { recette ->
            if (recette != null) {
                tvSoir.text = recette.nom
                layoutBtnsSoir.visibility = View.VISIBLE
            } else {
                tvSoir.text = "+ Ajouter un repas"
                layoutBtnsSoir.visibility = View.GONE
            }
        }

        // Observe la recette du matin
        viewModel.recetteMatin.observe(viewLifecycleOwner) { recette ->
            if (recette != null) {
                tvMatin.text = recette.nom
                layoutBtnsMatin.visibility = View.VISIBLE
            } else {
                tvMatin.text = "+ Ajouter un repas"
                layoutBtnsMatin.visibility = View.GONE
            }
        }
        // Ouvre le DatePicker au clic sur la date
        tvJour.setOnClickListener {
            val dateActuelle = viewModel.jourActuel.value ?: LocalDate.now()

            // Crée le DatePicker avec la date actuelle présélectionnée
            val datePicker = android.app.DatePickerDialog(
                requireContext(),
                { _, annee, mois, jour ->
                    // mois commence à 0 en Java, donc +1
                    val dateChoisie = java.time.LocalDate.of(annee, mois + 1, jour)
                    viewModel.allerALaDate(dateChoisie)
                },
                dateActuelle.year,
                dateActuelle.monthValue - 1,  // -1 car DatePicker commence à 0
                dateActuelle.dayOfMonth
            )
            datePicker.show()
        }

        // Clics pour ajouter/changer les repas
        tvMidi.setOnClickListener { ouvrirSelectionRecette("Midi") }
        btnChangerMidi.setOnClickListener { ouvrirSelectionRecette("Midi") }
        btnSupprimerMidi.setOnClickListener { viewModel.supprimerRepas("Midi") }

        tvSoir.setOnClickListener { ouvrirSelectionRecette("Soir") }
        btnChangerSoir.setOnClickListener { ouvrirSelectionRecette("Soir") }
        btnSupprimerSoir.setOnClickListener { viewModel.supprimerRepas("Soir") }

        tvMatin.setOnClickListener { ouvrirSelectionRecette("Matin") }
        btnChangerMatin.setOnClickListener { ouvrirSelectionRecette("Matin") }
        btnSupprimerMatin.setOnClickListener { viewModel.supprimerRepas("Matin") }
    }

    private fun ouvrirSelectionRecette(typeRepas: String) {
        SelectionRecetteDialog(typeRepas) { recette ->
            viewModel.ajouterRecette(recette.id, typeRepas)
        }.show(parentFragmentManager, "selection_recette")
    }

}