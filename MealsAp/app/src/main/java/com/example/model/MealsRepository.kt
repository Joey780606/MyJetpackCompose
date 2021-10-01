package com.example.model

import com.example.model.response.MealsCategoriesResponse

/**
 * Created by Joey 2021/10/01
 * (重要)
 */
class MealsRepository {
    fun getMeals(): MealsCategoriesResponse = MealsCategoriesResponse(arrayListOf()) //(重要)直接 = ,MealsCategoriesResponse class是在 Responses.kt裡
}