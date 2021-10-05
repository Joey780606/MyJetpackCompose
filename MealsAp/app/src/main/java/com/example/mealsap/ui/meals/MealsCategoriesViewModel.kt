package com.example.mealsap.ui.meals

import androidx.lifecycle.ViewModel
import com.example.model.MealsRepository
import com.example.model.response.MealResponse
import com.example.model.response.MealsCategoriesResponse

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {
    // inherit ViewModel()
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {   //Ch79, 6:59前
        repository.getMeals { response ->
            successCallback(response)
        }
    }
}