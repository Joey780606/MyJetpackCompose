package com.example.mealsap.ui.meals

import androidx.lifecycle.ViewModel
import com.example.model.MealsRepository
import com.example.model.response.MealResponse

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {
    // inherit ViewModel()
    suspend fun getMeals(): List<MealResponse> {   //Ch82
        return repository.getMeals().categories
    }
}