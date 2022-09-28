package com.example.foodapp.data

import com.example.foodapp.data.database.RecipesDao
import com.example.foodapp.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author : Mingaleev D
 * @data : 28/09/2022
 */

class LocalDataSource @Inject constructor(
  private val recipesDao: RecipesDao
) {

  fun readDatabase(): Flow<List<RecipesEntity>> = recipesDao.readRecipes()

  suspend fun insertRecipes(recipesEntity: RecipesEntity) = recipesDao.insertRecipes(recipesEntity)
}