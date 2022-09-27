package com.example.foodapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.RecipesRowLayoutBinding
import com.example.foodapp.models.FoodRecipe
import com.example.foodapp.models.Result
import com.example.foodapp.utils.RecipesDiffUtil

/**
 * @author : Mingaleev D
 * @data : 27/09/2022
 */

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

  private var recipes = emptyList<Result>()

  class RecipesViewHolder(private val binding: RecipesRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result) {
      binding.result = result
      binding.executePendingBindings()
    }

    companion object {
      fun from(parent: ViewGroup): RecipesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
        return RecipesViewHolder(binding)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder{
    return RecipesViewHolder.from(parent)
  }

  override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
    val currentRecipe = recipes[position]
    holder.bind(currentRecipe)

  }

  override fun getItemCount(): Int = recipes.size

  fun setData(newData: FoodRecipe) {
    val recipesDiffUtil =
      RecipesDiffUtil(recipes, newData.results)
    val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
    recipes = newData.results
    diffUtilResult.dispatchUpdatesTo(this)
  }
}