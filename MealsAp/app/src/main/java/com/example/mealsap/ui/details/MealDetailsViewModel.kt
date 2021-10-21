package com.example.mealsap.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.model.MealsRepository

import com.example.model.response.MealResponse

class MealDetailsViewModel(private val saveStateHandle: SavedStateHandle,
                           ): ViewModel() {
    private val repository: MealsRepository = MealsRepository.getInstance()

    var mealState = mutableStateOf<MealResponse?>(null)

    init {
        val mealId = saveStateHandle.get<String>("meal_category_id")?: ""

        mealState.value = repository.getMeal(mealId)
    }
}