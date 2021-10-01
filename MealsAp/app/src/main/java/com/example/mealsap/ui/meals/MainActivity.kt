package com.example.mealsap.ui.meals


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealsap.ui.theme.MealsApTheme

/**
 * Created by Joey 2021/10/01
 * (重要)
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
    val meals = viewModel.getMeals()
    Text(text = "Hello Compose!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealsApTheme {
        MealsCategoriesScreen()
    }
}