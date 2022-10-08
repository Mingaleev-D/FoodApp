package com.example.foodapp.ui.adapters

import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.data.database.entities.FavoritesEntity
import com.example.foodapp.databinding.FavoriteRowLayoutBinding
import com.example.foodapp.ui.fragments.FavoritesFragmentDirections
import com.example.foodapp.ui.viewmodel.MainViewModel
import com.example.foodapp.utils.RecipesDiffUtil
import com.google.android.material.snackbar.Snackbar

/**
 * @author : Mingaleev D
 * @data : 1/10/2022
 */

class FavoriteRecipesAdapter(
  private val requireActivity: FragmentActivity,
  private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewHolder>(),
  ActionMode.Callback {

  private var multiSelection = false
  private lateinit var mActionMode: ActionMode
  private lateinit var rootView: View

  private var favoriteRecipes = emptyList<FavoritesEntity>()

  private var selectedRecipes = arrayListOf<FavoritesEntity>()
  private var myViewHolders = arrayListOf<MyViewHolder>()

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
    myViewHolders.add(holder)
    rootView = holder.itemView.rootView

    val currentRecipe = favoriteRecipes[position]
    holder.bind(currentRecipe)

    saveItemStateOnScroll(currentRecipe, holder)

    //single click
    holder.binding.favoriteRecipesRowLayout.setOnClickListener {
      if (multiSelection) {
        applySelection(holder, currentRecipe)
      } else {
        val action =
          FavoritesFragmentDirections.actionFavoritesFragmentToDetailsActivity(currentRecipe.result)
        holder.itemView.findNavController().navigate(action)
      }
    }
    //long click
    holder.binding.favoriteRecipesRowLayout.setOnLongClickListener {
      if (!multiSelection) {
        multiSelection = true
        requireActivity.startActionMode(this)
        applySelection(holder, currentRecipe)
        true
      } else {
        applySelection(holder, currentRecipe)
        true
      }
    }
  }

  override fun getItemCount(): Int = favoriteRecipes.size

  fun setData(newFavoriteRecipes: List<FavoritesEntity>) {
    val favoriteRecipesDiffUtil =
      RecipesDiffUtil(favoriteRecipes, newFavoriteRecipes)
    val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
    favoriteRecipes = newFavoriteRecipes
    diffUtilResult.dispatchUpdatesTo(this)
  }

  override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
    actionMode?.menuInflater?.inflate(R.menu.favorites_context_menu, menu)
    mActionMode = actionMode!!
    applyStatusBarColor(R.color.contextualStatusBarColor)
    return true
  }

  override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
    return true
  }

  override fun onActionItemClicked(actionMode: ActionMode?, item: MenuItem?): Boolean {
    if (item?.itemId == R.id.delete_favorite_menu) {
      selectedRecipes.forEach {
        mainViewModel.deleteFavoriteRecipe(it)
      }
      showSnackBar("${selectedRecipes.size} Recipe/s removed.")

      multiSelection = false
      selectedRecipes.clear()
      actionMode?.finish()
    }
    return true
  }

  override fun onDestroyActionMode(actionMode: ActionMode?) {
    myViewHolders.forEach { holder ->
      changeRecipeStyle(
        holder,
        androidx.cardview.R.color.cardview_light_background,
        R.color.darkGray
      )
    }
    multiSelection = false
    selectedRecipes.clear()
    applyStatusBarColor(R.color.statusBarColor)
  }

  private fun applyStatusBarColor(color: Int) {
    requireActivity.window.statusBarColor =
      ContextCompat.getColor(requireActivity, color)
  }

  private fun applySelection(holder: MyViewHolder, currentRecipe: FavoritesEntity) {
    if (selectedRecipes.contains(currentRecipe)) {
      selectedRecipes.remove(currentRecipe)
      changeRecipeStyle(
        holder,
        androidx.cardview.R.color.cardview_dark_background,
        R.color.white
      )
      applyActionModeTitle()
    } else {
      selectedRecipes.add(currentRecipe)
      changeRecipeStyle(
        holder,
        androidx.cardview.R.color.cardview_light_background,
        R.color.colorPrimary
      )
      applyActionModeTitle()
    }
  }

  private fun changeRecipeStyle(holder: MyViewHolder, backgroundColor: Int, strokeColor: Int) {
    holder.binding.favoriteRecipesRowLayout.setBackgroundColor(
      ContextCompat.getColor(requireActivity, backgroundColor)
    )
    holder.binding.favoriteCardView.strokeColor =
      ContextCompat.getColor(requireActivity, strokeColor)
  }

  private fun applyActionModeTitle() {
    when (selectedRecipes.size) {
      0 -> {
        mActionMode.finish()
        multiSelection = false
      }
      1 -> {
        mActionMode.title = "${selectedRecipes.size} item selected"
      }
      else -> {
        mActionMode.title = "${selectedRecipes.size} items selected"
      }
    }
  }

  private fun saveItemStateOnScroll(currentRecipe: FavoritesEntity, holder: MyViewHolder) {
    if (selectedRecipes.contains(currentRecipe)) {
      changeRecipeStyle(
        holder,
        androidx.cardview.R.color.cardview_light_background,
        R.color.colorPrimary
      )
    } else {
      changeRecipeStyle(holder, androidx.cardview.R.color.cardview_dark_background, R.color.white)
    }
  }

  private fun showSnackBar(message: String) {
    Snackbar.make(
      rootView,
      message,
      Snackbar.LENGTH_SHORT
    ).setAction("Okay") {}
      .show()
  }

  fun clearContextualActionMode() {
    if (this::mActionMode.isInitialized) {
      mActionMode.finish()
    }
  }
}