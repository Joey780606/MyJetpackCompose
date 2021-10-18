package com.example.model

import com.example.model.api.MealsWebService
import com.example.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Joey 2021/10/01
 * (重要)
 */
class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    // MealsWebService is in MealsApi.kt
    suspend fun getMeals(): MealsCategoriesResponse {
        return webService.getMeals()
    }
}