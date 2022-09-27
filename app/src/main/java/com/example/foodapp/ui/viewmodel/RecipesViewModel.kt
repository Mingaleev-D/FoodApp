package com.example.foodapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.foodapp.utils.Constants.API_KEY
import com.example.foodapp.utils.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.example.foodapp.utils.Constants.QUERY_API_KEY
import com.example.foodapp.utils.Constants.QUERY_DIET
import com.example.foodapp.utils.Constants.QUERY_FILL_INGREDIENTS
import com.example.foodapp.utils.Constants.QUERY_NUMBER
import com.example.foodapp.utils.Constants.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel

/**
 * @author : Mingaleev D
 * @data : 27/09/2022
 */

class RecipesViewModel(
  application: Application
) : AndroidViewModel(application) {

  fun applyQueries(): HashMap<String, String> {
    val queries: HashMap<String, String> = HashMap()

    queries[QUERY_NUMBER] = "50"
    queries[QUERY_API_KEY] = API_KEY
    queries[QUERY_TYPE] = "snack"
    queries[QUERY_DIET] = "vegan"
    queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
    queries[QUERY_FILL_INGREDIENTS] = "true"

    return queries
  }
}