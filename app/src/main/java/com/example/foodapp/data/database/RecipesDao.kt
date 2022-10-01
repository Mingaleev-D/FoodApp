package com.example.foodapp.data.database

import androidx.room.*
import com.example.foodapp.data.database.entities.FavoritesEntity
import com.example.foodapp.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 28/09/2022
 */
@Dao
interface RecipesDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertRecipes(recipesEntity: RecipesEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

  @Query("SELECT * FROM recipes_table ORDER BY id ASC")
  fun readRecipes(): Flow<List<RecipesEntity>>

  @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
  fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

  @Delete
  suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

  @Query("DELETE FROM favorite_recipes_table")
  suspend fun deleteAllFavoriteRecipes()
}