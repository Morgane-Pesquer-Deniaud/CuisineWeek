package com.example.cuisineweek.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cuisineweek.R
import com.example.cuisineweek.data.entity.Recette

class RecetteAdapter(
    // lambda = fonction qu'on passera depuis le Fragment
    // pour savoir sur quelle recette l'utilisateur a cliqué
    private val onRecetteClick: (Recette) -> Unit
) : RecyclerView.Adapter<RecetteAdapter.RecetteViewHolder>() {

    // La liste des recettes affichées
    private var recettes: List<Recette> = emptyList()

    // ViewHolder = représente UNE carte dans la liste
    // il garde en mémoire les références aux TextView pour aller vite
    inner class RecetteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNom: TextView = itemView.findViewById(R.id.tvNomRecette)
        val tvTemps: TextView = itemView.findViewById(R.id.tvTemps)
        val tvDifficulte: TextView = itemView.findViewById(R.id.tvDifficulte)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    // Appelé par Android pour créer une nouvelle carte visuelle
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recette, parent, false)
        return RecetteViewHolder(view)
    }

    // Appelé pour remplir une carte avec les données d'une recette
    override fun onBindViewHolder(holder: RecetteViewHolder, position: Int) {
        val recette = recettes[position]

        holder.tvNom.text = recette.nom
        holder.tvTemps.text = "⏱ ${recette.tempsPrep + recette.tempsCuisson} min"
        holder.tvDifficulte.text = recette.difficulte
        holder.tvDescription.text = recette.description

        // Quand on clique sur une carte
        holder.itemView.setOnClickListener {
            onRecetteClick(recette)
        }
    }

    // Combien de cartes y a-t-il dans la liste ?
    override fun getItemCount() = recettes.size

    // Appelé depuis le Fragment pour mettre à jour la liste
    fun updateRecettes(nouvelles: List<Recette>) {
        recettes = nouvelles
        notifyDataSetChanged() // dit au RecyclerView de se redessiner
    }
}