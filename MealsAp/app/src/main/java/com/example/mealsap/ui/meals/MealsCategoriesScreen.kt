package com.example.mealsap.ui.meals

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.mealsap.ui.theme.MealsApTheme
import com.example.model.response.MealResponse


@Composable
fun MealsCategoriesScreen() {
    val viewModel : MealsCategoriesViewModel = viewModel() //(Ch74, 16:00有說明,簡單來說只要存在過,就不會再instantiated一次)
    val meals = viewModel.mealsState.value
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals) { meal ->
            MealCategory(meal)
        }
    }
}

@Composable
fun MealCategory(meal: MealResponse) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row {
            // Image
            Image(
                painter = rememberImagePainter(meal.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(88.dp)
                    .padding(4.dp)
            )

            Column(modifier = Modifier.align(Alignment.CenterVertically)
                .padding(16.dp)
            ) {
                Text(text = meal.name,
                style = MaterialTheme.typography.h6)
            }

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