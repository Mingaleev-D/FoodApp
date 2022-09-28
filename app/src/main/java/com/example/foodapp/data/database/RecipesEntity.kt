package com.example.foodapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.utils.Constants.RECIPES_TABLE

/**
 * @author : Mingaleev D
 * @data : 28/09/2022
 */
@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
  var foodRecipe: FoodRecipe
) {
  @PrimaryKey(autoGenerate = false)
  var id: Int = 0
}