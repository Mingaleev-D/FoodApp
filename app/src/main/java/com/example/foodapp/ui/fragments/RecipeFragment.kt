package com.example.foodapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentRecipeBinding
import com.example.foodapp.ui.adapters.RecipesAdapter
import com.example.foodapp.ui.viewmodel.MainViewModel
import com.example.foodapp.ui.viewmodel.RecipesViewModel
import com.example.foodapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment : Fragment() {

  private var mBinding: FragmentRecipeBinding? = null
  private val binding get() = mBinding!!

  private lateinit var mainViewModel: MainViewModel
  private lateinit var recipesViewModel: RecipesViewModel

  private val mAdapter by lazy { RecipesAdapter() }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    mBinding = FragmentRecipeBinding.inflate(layoutInflater)
    binding.recyclerview.showShimmer()

    setupRecyclerView()
    requestApiData()
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

  }

  private fun requestApiData() {
    mainViewModel.getRecipes(recipesViewModel.applyQueries())
    mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
      when (response) {
        is NetworkResult.Success -> {
          hideShimmerEffect()
          response.data?.let { mAdapter.setData(it) }
        }
        is NetworkResult.Error -> {
          hideShimmerEffect()
          Toast.makeText(
            requireContext(),
            response.message.toString(),
            Toast.LENGTH_SHORT
          ).show()
        }
        is NetworkResult.Loading -> {
          showShimmerEffect()
        }
      }
    }
  }

  private fun setupRecyclerView() {
   binding.recyclerview.adapter = mAdapter
    binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
    showShimmerEffect()
  }

  private fun showShimmerEffect() {
    binding.recyclerview.showShimmer()
  }

  private fun hideShimmerEffect() {
    binding.recyclerview.hideShimmer()
  }


  override fun onDestroy() {
    super.onDestroy()
    mBinding = null
  }


}