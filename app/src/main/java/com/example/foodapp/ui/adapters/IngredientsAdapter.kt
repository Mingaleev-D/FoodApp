package com.example.foodapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foodapp.R
import com.example.foodapp.databinding.IngredientsRowLayoutBinding
import com.example.foodapp.models.ExtendedIngredient
import com.example.foodapp.utils.Constants.BASE_IMAGE_URL
import com.example.foodapp.utils.RecipesDiffUtil
import java.util.*

/**
 * @author : Mingaleev D
 * @data : 30/09/2022
 */

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

  private var ingredientsList = emptyList<ExtendedIngredient>()

  class MyViewHolder(val binding: IngredientsRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
    MyViewHolder(
      IngredientsRowLayoutBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.binding.ingredientImageView.load(BASE_IMAGE_URL + ingredientsList[position].image) {
      crossfade(600)
      error(R.drawable.ic_error)
    }
    holder.binding.ingredientName.text = ingredientsList[position].name.capitalize(Locale.ROOT)
    holder.binding.ingredientAmount.text = ingredientsList[position].amount.toString()
    holder.binding.ingredientUnit.text = ingredientsList[position].unit
    holder.binding.ingredientConsistency.text = ingredientsList[position].consistency
    holder.binding.ingredientOriginal.text = ingredientsList[position].original
  }

  override fun getItemCount(): Int = ingredientsList.size

  fun setData(newIngredients: List<ExtendedIngredient>) {
    val ingredientsDiffUtil =
      RecipesDiffUtil(ingredientsList, newIngredients)
    val diffUtilResult = DiffUtil.calculateDiff(ingredientsDiffUtil)
    ingredientsList = newIngredients
    diffUtilResult.dispatchUpdatesTo(this)
  }
}