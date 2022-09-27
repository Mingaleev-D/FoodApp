package com.example.foodapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  lateinit var navController: NavController
  lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)



    navController = findNavController(R.id.fragmentContainerView)
    val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.recipeFragment,
        R.id.favoritesFragment,
        R.id.jokeFragment
      )
    )

    binding.btnNavMenu.setupWithNavController(navController)
    setupActionBarWithNavController(navController, appBarConfiguration)

  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp() || super.onSupportNavigateUp()
  }
}
