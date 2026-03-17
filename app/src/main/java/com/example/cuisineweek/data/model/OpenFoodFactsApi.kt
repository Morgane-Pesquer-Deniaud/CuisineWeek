package com.example.cuisineweek.data.model

import retrofit2.http.GET
import retrofit2.http.Path

interface OpenFoodFactsApi {

    // @GET = requête HTTP GET
    // {barcode} = paramètre variable (le code-barre scanné)
    @GET("product/{barcode}.json")
    suspend fun getProduct(
        @Path("barcode") barcode: String
    ): OpenFoodFactsResponse
}