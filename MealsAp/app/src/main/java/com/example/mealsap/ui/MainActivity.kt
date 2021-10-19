package com.example.mealsap.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mealsap.ui.meals.MealsCategoriesScreen
import com.example.mealsap.ui.theme.MealsApTheme

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
