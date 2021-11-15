package com.example.profilecardlayout.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val VeryLightGrey = Color(0x60DCDCDC)
val LightGreen200 = Color(0x9953CD32)

//@Composable  //若寫在這,會有 This annotation is not applicable(合適的) to target 'top level property without backing field or deleqate' 的錯誤,要改到下面才可以
val Colors.lightGreen
    @Composable
    get() = LightGreen200