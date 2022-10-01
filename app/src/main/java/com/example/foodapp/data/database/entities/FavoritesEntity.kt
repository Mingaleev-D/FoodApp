package com.example.foodapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodapp.models.Result
import com.example.foodapp.utils.Constants.FAVORITE_RECIPES_TABLE

/**
 * @author : Mingaleev D
 * @data : 30/09/2022
 */
@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
  @PrimaryKey(autoGenerate = true)
  var id: Int,
  var result: Result
) {
}