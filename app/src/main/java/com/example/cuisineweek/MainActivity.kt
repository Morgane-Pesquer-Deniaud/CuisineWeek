package com.example.cuisineweek

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cuisineweek.ui.CoursesFragment
import com.example.cuisineweek.ui.RecettesFragment
import com.example.cuisineweek.ui.SemaineFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Fragment affiché au démarrage
        if (savedInstanceState == null) {
            chargerFragment(RecettesFragment())
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_semaine  -> chargerFragment(SemaineFragment())
                R.id.nav_recettes -> chargerFragment(RecettesFragment())
                R.id.nav_courses  -> chargerFragment(CoursesFragment())
            }
            true
        }
    }

    private fun chargerFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}