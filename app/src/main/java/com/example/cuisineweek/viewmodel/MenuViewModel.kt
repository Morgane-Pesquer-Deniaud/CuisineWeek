package com.example.cuisineweek.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cuisineweek.data.database.AppDatabase
import com.example.cuisineweek.data.entity.MenuRecette
import com.example.cuisineweek.data.entity.MenuSemaine
import com.example.cuisineweek.data.entity.Recette
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    private val menuDao = AppDatabase.getDatabase(application).menuDao()
    private val recetteDao = AppDatabase.getDatabase(application).recetteDao()

    // Le jour actuellement affiché
    private val _jourActuel = MutableLiveData<LocalDate>(LocalDate.now())
    val jourActuel: LiveData<LocalDate> = _jourActuel

    // Les recettes du jour affiché
    private val _recetteMidi = MutableLiveData<Recette?>(null)
    val recetteMidi: LiveData<Recette?> = _recetteMidi

    private val _recetteSoir = MutableLiveData<Recette?>(null)
    val recetteSoir: LiveData<Recette?> = _recetteSoir

    private val _recetteMatin = MutableLiveData<Recette?>(null)
    val recetteMatin: LiveData<Recette?> = _recetteMatin

    // ID du menu de la semaine en cours
    private var menuId: Int = -1

    init {
        chargerMenuDuJour()
    }

    // Passer au jour suivant
    fun jourSuivant() {
        _jourActuel.value = _jourActuel.value?.plusDays(1)
        chargerMenuDuJour()
    }

    // Passer au jour précédent
    fun jourPrecedent() {
        _jourActuel.value = _jourActuel.value?.minusDays(1)
        chargerMenuDuJour()
    }

    // Charge ou crée le menu de la semaine et récupère les recettes du jour
    fun chargerMenuDuJour() = viewModelScope.launch {
        val jour = _jourActuel.value ?: return@launch

        // On utilise le lundi de la semaine comme identifiant
        val lundiSemaine = jour.minusDays(jour.dayOfWeek.value.toLong() - 1)
        val semaineDu = lundiSemaine.format(DateTimeFormatter.ISO_DATE)

        // Récupère ou crée le menu de cette semaine
        var menu = menuDao.getMenuBySemaine(semaineDu)
        if (menu == null) {
            val id = menuDao.insertMenu(MenuSemaine(semaineDu = semaineDu))
            menuId = id.toInt()
        } else {
            menuId = menu.id
        }

        // Récupère les recettes du jour
        val nomJour = getNomJour(jour)
        _recetteMidi.postValue(getRecettePourRepas(nomJour, "Midi"))
        _recetteSoir.postValue(getRecettePourRepas(nomJour, "Soir"))
        _recetteMatin.postValue(getRecettePourRepas(nomJour, "Matin"))
    }

    // Ajouter une recette à un repas
    fun ajouterRecette(recetteId: Int, typeRepas: String) = viewModelScope.launch {
        val jour = _jourActuel.value ?: return@launch
        val nomJour = getNomJour(jour)

        // Supprime l'ancien repas s'il existe
        supprimerRepas(typeRepas)

        // Insère le nouveau
        menuDao.insertMenuRecette(
            MenuRecette(
                menuId = menuId,
                recetteId = recetteId,
                jour = nomJour,
                typeRepas = typeRepas
            )
        )

        // Rafraîchit l'affichage
        chargerMenuDuJour()
    }

    // Supprimer un repas
    fun supprimerRepas(typeRepas: String) = viewModelScope.launch {
        val jour = _jourActuel.value ?: return@launch
        val nomJour = getNomJour(jour)
        menuDao.deleteMenuRecette(menuId, nomJour, typeRepas)
        chargerMenuDuJour()
    }

    // Récupère la recette d'un repas pour le jour actuel
    private suspend fun getRecettePourRepas(jour: String, typeRepas: String): Recette? {
        val recetteId = menuDao.getRecetteIdPourRepas(menuId, jour, typeRepas)
        return recetteId?.let { recetteDao.getRecetteById(it) }
    }

    // Convertit une date en nom de jour français
    fun getNomJour(date: LocalDate): String {
        return when (date.dayOfWeek.value) {
            1 -> "Lundi"
            2 -> "Mardi"
            3 -> "Mercredi"
            4 -> "Jeudi"
            5 -> "Vendredi"
            6 -> "Samedi"
            7 -> "Dimanche"
            else -> ""
        }
    }

    // Formatte la date pour l'affichage
    fun getDateFormatee(date: LocalDate): String {
        val mois = when (date.monthValue) {
            1 -> "janvier"; 2 -> "février"; 3 -> "mars"
            4 -> "avril"; 5 -> "mai"; 6 -> "juin"
            7 -> "juillet"; 8 -> "août"; 9 -> "septembre"
            10 -> "octobre"; 11 -> "novembre"; 12 -> "décembre"
            else -> ""
        }
        return "${getNomJour(date)} ${date.dayOfMonth} $mois"
    }

    fun allerALaDate(date: LocalDate) {
        _jourActuel.value = date
        chargerMenuDuJour()
    }
}