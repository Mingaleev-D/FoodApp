package com.example.foodapp.data

import com.example.foodapp.data.api.FoodRecipesApi
import com.example.foodapp.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 27/09/2022
 */

class RemoteDataSource @Inject constructor(
  private val foodRecipesApi: FoodRecipesApi
) {

  suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> =
    foodRecipesApi.getRecipes(queries)

}