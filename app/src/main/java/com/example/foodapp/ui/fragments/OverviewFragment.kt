package com.example.foodapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import coil.load
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentOverviewBinding
import com.example.foodapp.models.Result
import com.example.foodapp.ui.adapters.bindingadapters.RecipesRowBinding
import com.example.foodapp.utils.Constants.RECIPE_RESULT_KEY


class OverviewFragment : Fragment() {

  private var mBinding: FragmentOverviewBinding? = null
  private val binding get() = mBinding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    mBinding = FragmentOverviewBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val args = arguments
    val myBundle: com.example.foodapp.models.Result =
      args!!.getParcelable<Result>(RECIPE_RESULT_KEY) as Result

    binding.mainImageView.load(myBundle.image)
    binding.titleTextView.text = myBundle.title
    binding.likesTextView.text = myBundle.aggregateLikes.toString()
    binding.timeTextView.text = myBundle.readyInMinutes.toString()
    RecipesRowBinding.parseHtml(binding.summaryTextView, myBundle.summary)

    updateColors(myBundle.vegetarian, binding.vegetarianTextView, binding.vegetarianImageView)
    updateColors(myBundle.vegan, binding.veganTextView, binding.veganImageView)
    updateColors(myBundle.cheap, binding.cheapTextView, binding.cheapImageView)
    updateColors(myBundle.dairyFree, binding.dairyFreeTextView, binding.dairyFreeImageView)
    updateColors(myBundle.glutenFree, binding.glutenFreeTextView, binding.glutenFreeImageView)
    updateColors(myBundle.veryHealthy, binding.healthyTextView, binding.healthyImageView)
  }

  private fun updateColors(stateIsOn: Boolean, textView: TextView, imageView: ImageView) {
    if (stateIsOn) {
      imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
      textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    mBinding = null
  }
}