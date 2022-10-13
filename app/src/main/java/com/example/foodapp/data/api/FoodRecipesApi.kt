package com.example.foodapp.data.api

import com.example.foodapp.models.FoodJoke
import com.example.foodapp.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
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

  @GET("food/jokes/random")
  suspend fun getFoodJoke(
    @Query("apiKey") apiKey: String
  ): Response<FoodJoke>
}