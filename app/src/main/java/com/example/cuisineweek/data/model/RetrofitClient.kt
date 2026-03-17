package com.example.cuisineweek.data.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // URL de base de l'API OpenFoodFacts
    private const val BASE_URL = "https://world.openfoodfacts.org/api/v0/"

    // Singleton Retrofit — créé une seule fois
    val api: OpenFoodFactsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // GsonConverterFactory = convertit automatiquement
            // le JSON en classes Kotlin
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenFoodFactsApi::class.java)
    }
}