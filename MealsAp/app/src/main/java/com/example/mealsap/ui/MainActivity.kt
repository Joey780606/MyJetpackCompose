package com.example.mealsap.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealsap.ui.details.MealDetailsScreen
import com.example.mealsap.ui.details.MealDetailsViewModel
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
                FoodiezApp()
            }
        }
    }
}

@Composable
private fun FoodiezApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "destination_meals_list") {
        composable(route = "destination_meals_list") {
            MealsCategoriesScreen { navigationMealId ->
                navController.navigate("destination_meal_details/$navigationMealId")
            }
        }
        composable(
            route = "destination_meal_details/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id") { // pass id
                type = NavType.StringType
            })
        ) {
            val viewModel: MealDetailsViewModel = viewModel()
            MealDetailsScreen(viewModel.mealState.value)
        }
    }

}