package com.example.foodapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentIngredientsBinding
import com.example.foodapp.databinding.FragmentInstructionsBinding
import com.example.foodapp.models.Result
import com.example.foodapp.utils.Constants


class InstructionsFragment : Fragment() {

  private var mBinding: FragmentInstructionsBinding? = null
  private val binding get() = mBinding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    mBinding = FragmentInstructionsBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val args = arguments
    val myBundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT_KEY)

    binding.instructionsWebView.webViewClient = object : WebViewClient() {}
    val websiteUrl: String = myBundle!!.sourceUrl
    binding.instructionsWebView.loadUrl(websiteUrl)
  }

  override fun onDestroy() {
    super.onDestroy()
    mBinding = null
  }


}