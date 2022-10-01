package com.example.foodapp.data

import com.example.foodapp.data.database.RecipesDao
import com.example.foodapp.data.database.entities.FavoritesEntity
import com.example.foodapp.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 28/09/2022
 */

class LocalDataSource @Inject constructor(
  private val recipesDao: RecipesDao
) {

  fun readRecipes(): Flow<List<RecipesEntity>> = recipesDao.readRecipes()

  fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> =
    recipesDao.readFavoriteRecipes()

  suspend fun insertRecipes(recipesEntity: RecipesEntity) = recipesDao.insertRecipes(recipesEntity)

  suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) =
    recipesDao.insertFavoriteRecipe(favoritesEntity)


  suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) =
    recipesDao.deleteFavoriteRecipe(favoritesEntity)

  suspend fun deleteAllFavoriteRecipes() = recipesDao.deleteAllFavoriteRecipes()

}