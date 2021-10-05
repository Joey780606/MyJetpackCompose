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

    fun getMeals(): Call<MealsCategoriesResponse> {  // MealsCategoriesResponse is in Responses.kt
        return api.getMeals()
    }

    interface MealsApi {
        @GET("categories.php")
        fun getMeals(): Call<MealsCategoriesResponse>
    }
}