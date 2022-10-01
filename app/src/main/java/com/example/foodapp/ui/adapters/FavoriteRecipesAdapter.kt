package com.example.foodapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.database.entities.FavoritesEntity
import com.example.foodapp.databinding.FavoriteRowLayoutBinding
import com.example.foodapp.utils.RecipesDiffUtil

/**
 * @author : Mingaleev D
 * @data : 1/10/2022
 */

class FavoriteRecipesAdapter : RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewHolder>() {

  private var favoriteRecipes = emptyList<FavoritesEntity>()

  class MyViewHolder(val binding: FavoriteRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(favoritesEntity: FavoritesEntity) {
      binding.favoritesEntity = favoritesEntity
      binding.executePendingBindings()
    }

    companion object {
      fun from(parent: ViewGroup): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FavoriteRowLayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
    MyViewHolder.from(parent)

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    val selectedRecipe = favoriteRecipes[position]
    holder.bind(selectedRecipe)
  }

  override fun getItemCount(): Int = favoriteRecipes.size

  fun setData(newFavoriteRecipes: List<FavoritesEntity>) {
    val favoriteRecipesDiffUtil =
      RecipesDiffUtil(favoriteRecipes, newFavoriteRecipes)
    val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
    favoriteRecipes = newFavoriteRecipes
    diffUtilResult.dispatchUpdatesTo(this)
  }
}