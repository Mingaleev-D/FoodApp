package com.example.foodapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentJokeBinding
import com.example.foodapp.ui.viewmodel.MainViewModel
import com.example.foodapp.utils.Constants.API_KEY
import com.example.foodapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JokeFragment : Fragment() {

  private var _binding: FragmentJokeBinding? = null
  private val binding get() = _binding!!

  private val mainViewModel by viewModels<MainViewModel>()

  private var foodJoke = "No Food Joke"

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentJokeBinding.inflate(layoutInflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.lifecycleOwner = viewLifecycleOwner
    binding.mainViewModel = mainViewModel

    setHasOptionsMenu(true)

    mainViewModel.getFoodJoke(API_KEY)

    mainViewModel.foodJokeResponse.observe(viewLifecycleOwner) { response ->
      when (response) {
        is NetworkResult.Success -> {
          binding.textJokeFood.text = response.data?.text
          if (response.data != null) {
            foodJoke = response.data.text
          }
        }
        is NetworkResult.Error -> {
          loadDataFromCache()
          Toast.makeText(
            requireContext(),
            response.message.toString(),
            Toast.LENGTH_SHORT
          ).show()
        }
        is NetworkResult.Loading -> {
          Log.d("JokeFragment", "Loading")
        }
      }
    }
  }

  private fun loadDataFromCache() {
    lifecycleScope.launch {
      mainViewModel.readFoodJoke.observe(viewLifecycleOwner, { database ->
        if (!database.isNullOrEmpty()) {
          binding.textJokeFood.text = database[0].foodJoke.text
           foodJoke = database[0].foodJoke.text
        }
      })
    }
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    inflater.inflate(R.menu.joke_menu, menu)

  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.share_food_joke_menu) {
      val shareIntent = Intent().apply {
        this.action = Intent.ACTION_SEND
        this.putExtra(Intent.EXTRA_TEXT, foodJoke)
        this.type = "text/plain"
      }
      startActivity(shareIntent)
    }
    return super.onOptionsItemSelected(item)
  }


  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }


}