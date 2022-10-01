package com.example.foodapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodapp.data.database.entities.FavoritesEntity
import com.example.foodapp.data.database.entities.RecipesEntity

/**
 * @author : Mingaleev D
 * @data : 28/09/2022
 */
@Database(entities = [RecipesEntity::class, FavoritesEntity::class], version = 1, exportSchema = false)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase:RoomDatabase() {

  abstract fun recipesDao():RecipesDao
}