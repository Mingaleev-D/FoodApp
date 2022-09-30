package com.example.foodapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.databinding.FragmentIngredientsBinding
import com.example.foodapp.models.Result
import com.example.foodapp.ui.adapters.IngredientsAdapter
import com.example.foodapp.utils.Constants.RECIPE_RESULT_KEY


class IngredientsFragment : Fragment() {

  private var mBinding: FragmentIngredientsBinding? = null
  private val binding get() = mBinding!!

  private val mAdapter:IngredientsAdapter by lazy { IngredientsAdapter() }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    mBinding = FragmentIngredientsBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val args = arguments
    val myBundle:Result? = args?.getParcelable(RECIPE_RESULT_KEY)

    setupRecyclerView()
    myBundle?.extendedIngredients?.let { mAdapter.setData(it) }
  }

  private fun setupRecyclerView() {
    binding.rvIngredients.adapter = mAdapter
    binding.rvIngredients.layoutManager = LinearLayoutManager(requireContext())
  }

  override fun onDestroy() {
    super.onDestroy()
    mBinding = null
  }


}