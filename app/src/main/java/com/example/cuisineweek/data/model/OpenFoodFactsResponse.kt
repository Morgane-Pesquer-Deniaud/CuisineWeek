package com.example.cuisineweek.data.model

import com.google.gson.annotations.SerializedName

// Cette classe représente la réponse complète de l'API
data class OpenFoodFactsResponse(
    val status: Int,       // 1 = produit trouvé, 0 = pas trouvé
    val product: Product?  // les infos du produit (peut être null)
)

data class Product(
    // @SerializedName = le nom du champ dans le JSON
    @SerializedName("product_name")
    val nom: String?,

    @SerializedName("quantity")
    val quantite: String?,

    @SerializedName("nutrition_grades")
    val nutriscore: String?
)