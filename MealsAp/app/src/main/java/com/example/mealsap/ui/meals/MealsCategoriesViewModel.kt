package com.example.mealsap.ui.meals

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.MealsRepository
import com.example.model.response.MealResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoriesViewModel (private val repository: MealsRepository = MealsRepository()): ViewModel() {
    // inherit ViewModel()

    /* Ch83 before 5:00
    private val mealsJob = Job()
    init {
        val scope = CoroutineScope(mealsJob + Dispatchers.IO)
        scope.launch() {
            val meals = getMeals()
            mealsState.value = meals
        }
    }
    val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())  //Not need remember because lifecycle of viewModel

    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }
    */

    //  Ch83 after 5:00
    init {
        Log.d("MealViewModel", "MealViewModel 001: about to launch a coroutine") //1
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("MealViewModel", "MealViewModel 002: have launched the coroutine") //3
            val meals = getMeals()
            Log.d("MealViewModel", "MealViewModel 003: have received the async data") //4
            mealsState.value = meals
        }
        Log.d("MealViewModel", "MealViewModel 004: other work") //2
    }
    val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())  //Not need remember because lifecycle of viewModel

    suspend fun getMeals(): List<MealResponse> {   //Ch82
        return repository.getMeals().categories
    }
}