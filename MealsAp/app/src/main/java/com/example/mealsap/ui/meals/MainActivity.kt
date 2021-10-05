package com.example.mealsap.ui.meals


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealsap.ui.theme.MealsApTheme
import com.example.model.response.MealResponse

/**
 * Created by Joey 2021/10/01
 *
 * //Ch79: 順序: MealsRepository.kt , MealsCategoriesViewModel.kt , MainActivity.kt
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val viewModel by viewModels<MealsCategoriesViewModel>() // 雖沒用,但(重要) by有委託的意思 , ch74 13:00左右
        setContent {
            MealsApTheme {
                MealsCategoriesScreen()
            }
        }
    }
}

@Composable
fun MealsCategoriesScreen() {
    val viewModel : MealsCategoriesViewModel = viewModel() //(Ch74, 16:00有說明,簡單來說只要存在過,就不會再instantiated一次)
    val rememberedMeals: MutableState<List<MealResponse>> = remember { mutableStateOf(emptyList<MealResponse>()) }
    viewModel.getMeals { response ->
        val mealsFromTheApi = response?.categories
        rememberedMeals.value = mealsFromTheApi.orEmpty()
    }
    LazyColumn {
        items(rememberedMeals.value) { meal ->
            Text(text = meal.name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealsApTheme {
        MealsCategoriesScreen()
    }
}