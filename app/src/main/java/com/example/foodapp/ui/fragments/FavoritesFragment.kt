package com.example.foodapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentFavoritesBinding
import com.example.foodapp.ui.adapters.FavoriteRecipesAdapter
import com.example.foodapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

  private var mBinding: FragmentFavoritesBinding? = null
  private val binding get() = mBinding!!

  private val mAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter() }
  private val mainViewModel: MainViewModel by viewModels()

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

  private fun setupRecyclerView(recyclerView: RecyclerView) {
    recyclerView.adapter = mAdapter
    recyclerView.layoutManager = LinearLayoutManager(requireContext())
  }


  override fun onDestroy() {
    super.onDestroy()
    mBinding = null
  }

}