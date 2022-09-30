package com.example.foodapp.data.api

import com.example.foodapp.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * @author : Mingaleev D
 * @data : 27/09/2022
 */

interface FoodRecipesApi {

  @GET("/recipes/complexSearch")
  suspend fun getRecipes(
    @QueryMap queries: Map<String, String>
  ): Response<FoodRecipe>

  @GET("/recipes/complexSearch")
  suspend fun searchRecipes(
    @QueryMap searchQuery: Map<String, String>
  ): Response<FoodRecipe>
}