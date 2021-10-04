package com.example.model

import com.example.model.api.MealsWebService
import com.example.model.response.MealsCategoriesResponse

/**
 * Created by Joey 2021/10/01
 * (重要)
 */
class MealsRepository(private val webServices: MealsWebService = MealsWebService()) {
    // MealsWebService is in MealsApi.kt
    fun getMeals(): MealsCategoriesResponse? {  // MealsCategoriesResponse is in Responses.kt, attention ?
        return webServices.getMeals().execute().body()  // Bad practice, 因為要等一段時間才會回來,會把Main thread弄掛, AS已不讓人這樣做了
    }
}