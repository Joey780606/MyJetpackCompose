package com.example.model.api

import com.example.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Joey 2021/10/04
 *  Reference website:
 *  1. https://square.github.io/retrofit/
 *  2. https://www.themealdb.com/
 *  3. Ch82: Before 1:50
 */

class MealsWebService {  //A class that returns the actual retrofit response
    private lateinit var api: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    suspend fun getMeals(): MealsCategoriesResponse {  // MealsCategoriesResponse is in Responses.kt
        return api.getMeals()
    }

    interface MealsApi {
        @GET("categories.php")
        suspend fun getMeals(): MealsCategoriesResponse
    }
}