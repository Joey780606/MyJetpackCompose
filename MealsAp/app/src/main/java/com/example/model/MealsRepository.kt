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
    fun getMeals(successCallback: (response: MealsCategoriesResponse?) -> Unit) {   //Ch79(修改1), 5:09之前
        return webService.getMeals().enqueue(object: Callback<MealsCategoriesResponse> {
            // Ctrl + o Override methods
            // This is an asynchronous process.
            override fun onResponse(
                call: Call<MealsCategoriesResponse>,
                response: Response<MealsCategoriesResponse>
            ) {
                if(response.isSuccessful)
                    successCallback(response.body())
            }

            override fun onFailure(p0: Call<MealsCategoriesResponse>, p1: Throwable) {
                // TODO treat failure
            }
        })
    }
}