package com.example.foodapp.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentFavoritesBinding
import com.example.foodapp.ui.adapters.FavoriteRecipesAdapter
import com.example.foodapp.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

  private var mBinding: FragmentFavoritesBinding? = null
  private val binding get() = mBinding!!

  private val mainViewModel: MainViewModel by viewModels()
  private val mAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter(requireActivity(),mainViewModel) }


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    mBinding = FragmentFavoritesBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.lifecycleOwner = this
    binding.mainViewModel = mainViewModel
    binding.mAdapter = mAdapter

    setupRecyclerView(binding.rvFavorites)
  }
  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.favorites_context_menu, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if(item.itemId == R.id.deleteAll_favorite_recipes_menu){
      mainViewModel.deleteAllFavoriteRecipes()
      showSnackBar()
    }
    return super.onOptionsItemSelected(item)
  }

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = mAdapter
    recyclerView.layoutManager = LinearLayoutManager(requireContext())
  }
  private fun showSnackBar(){
    Snackbar.make(
      binding.root,
      "All recipes removed.",
      Snackbar.LENGTH_SHORT
    ).setAction("Okay"){}
      .show()
  }

  override fun onDestroy() {
    super.onDestroy()
    mBinding = null
    mAdapter.clearContextualActionMode()
  }

}