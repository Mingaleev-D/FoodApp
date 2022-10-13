package com.example.foodapp.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodapp.models.FoodJoke
import com.example.foodapp.utils.Constants.FOOD_JOKE_TABLE

/**
 * @author : Mingaleev D
 * @data : 13/10/2022
 */
@Entity(tableName = FOOD_JOKE_TABLE)
class FoodJokeEntity(
  @Embedded
  var foodJoke: FoodJoke
) {
  @PrimaryKey(autoGenerate = false)
  var id:Int = 0
}
