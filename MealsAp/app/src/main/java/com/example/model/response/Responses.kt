package com.example.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Joey 2021/10/01
 * (重要)
 */

// Feedback from server (Ch77 2:37),這是針對Json回應結構的第一個 data class (看Ch77 5:22)
//data class MealsCategoriesResponse(val categories: List<Any>)
data class MealsCategoriesResponse(val categories: List<MealResponse>) //上面不行,這行才可以,參數型態要對
  // 因為 Json 回應欄位名就是categories,所以不用跟下面一樣用 @SerializeName (看Ch77 11:00)

// 這是針對Json回應結構的第一個 data class (看Ch77 5:22)
data class MealResponse(
    // idCategory 是 Json 回應的欄位名, id是 class內的欄位名,為了要match在一起,要implement gson library,
    // 和使用 @SerializeName  (看Ch77 7:00)
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("strCategoryDescription") val description: String,
    @SerializedName("strCategoryThumb") val imageUrl: String
)