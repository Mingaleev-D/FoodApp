package com.example.foodapp.ui.fragments.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.foodapp.databinding.FragmentRecipesBottomSheetBinding
import com.example.foodapp.ui.viewmodel.RecipesViewModel
import com.example.foodapp.utils.Constants.DEFAULT_DIET_TYPE
import com.example.foodapp.utils.Constants.DEFAULT_MEAL_TYPE
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

class RecipesBottomSheetFragment : BottomSheetDialogFragment() {

  private var mBinding: FragmentRecipesBottomSheetBinding? = null
  private val binding get() = mBinding!!

  private lateinit var recipesViewModel: RecipesViewModel

  private var mealTypeChip = DEFAULT_MEAL_TYPE
  private var mealTypeChipId = 0
  private var dietTypeChip = DEFAULT_DIET_TYPE
  private var dietTypeChipId = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    mBinding = FragmentRecipesBottomSheetBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner) { value ->
      mealTypeChip = value.selectedMealType
      dietTypeChip = value.selectedDietType
      updateChip(value.selectedMealTypeId, binding.mealTypeGroupChip)
      updateChip(value.selectedDietTypeId, binding.dietTypeChipGroup)
    }

    binding.mealTypeGroupChip.setOnCheckedChangeListener { group, selectedChipId ->
      val chip = group.findViewById<Chip>(selectedChipId)
      val selectedMealType = chip.text.toString().lowercase(Locale.ROOT)
      mealTypeChip = selectedMealType
      mealTypeChipId = selectedChipId
    }

    binding.dietTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
      val chip = group.findViewById<Chip>(selectedChipId)
      val selectedDietType = chip.text.toString().lowercase(Locale.ROOT)
      dietTypeChip = selectedDietType
      dietTypeChipId = selectedChipId
    }

    binding.applyBtn.setOnClickListener {
      recipesViewModel.saveMealAndDietType(
        mealTypeChip,
        mealTypeChipId,
        dietTypeChip,
        dietTypeChipId
      )
      val action =
        RecipesBottomSheetFragmentDirections.actionRecipesBottomSheetFragmentToRecipeFragment(true)
      findNavController().navigate(action)
    }
  }

  private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
    if (chipId != 0) {
      try {
        chipGroup.findViewById<Chip>(chipId).isChecked = true
      } catch (e: Exception) {
        Log.d("TAG", "updateChip: ${e.message.toString()}")
      }

    }

  }


  override fun onDestroyView() {
    super.onDestroyView()
    mBinding = null
  }
}