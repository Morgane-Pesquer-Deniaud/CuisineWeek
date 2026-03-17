package com.example.cuisineweek.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cuisineweek.R
import com.example.cuisineweek.data.entity.ArticleCourses

class ArticleCoursesAdapter(
    private val onCocheChange: (ArticleCourses) -> Unit,
    private val onSupprimer: (ArticleCourses) -> Unit
) : RecyclerView.Adapter<ArticleCoursesAdapter.ArticleViewHolder>() {

    private var articles: List<ArticleCourses> = emptyList()

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBoxArticle)
        val tvNom: TextView = itemView.findViewById(R.id.tvNomArticle)
        val tvQuantite: TextView = itemView.findViewById(R.id.tvQuantiteArticle)
        val btnSupprimer: ImageButton = itemView.findViewById(R.id.btnSupprimerArticle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article_courses, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]

        holder.tvNom.text = article.nom
        holder.tvQuantite.text = article.quantite
        holder.checkBox.isChecked = article.coche

        // Si coché → texte barré
        if (article.coche) {
            holder.tvNom.paintFlags = holder.tvNom.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.tvNom.paintFlags = holder.tvNom.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        holder.checkBox.setOnClickListener { onCocheChange(article) }
        holder.btnSupprimer.setOnClickListener { onSupprimer(article) }
    }

    override fun getItemCount() = articles.size

    fun updateArticles(nouveaux: List<ArticleCourses>) {
        articles = nouveaux
        notifyDataSetChanged()
    }
}