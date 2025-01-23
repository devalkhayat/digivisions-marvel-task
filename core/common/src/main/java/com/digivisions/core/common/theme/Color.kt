package com.digivisions.core.common.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
//
val AppBlack = Color(0xFF000000)
val AppWhite = Color(0xFFFFFFFF)
val AppBlue = Color(0xFF2196F3)
val AppPink = Color(0xFF9C27B0)
val AppRed = Color(0xFFE3005E)
val AppGreen = Color(0xFF282C30)
val AppGray=Color(0xFF282C30)

//
fun  getColor(type:AppColors):Color{
    return when(type){

        AppColors.ScreenBackground -> AppBlack
        AppColors.Text1 -> AppBlack
        AppColors.Text2 -> AppRed
        AppColors.Text3 -> AppWhite
        AppColors.CircleProgress-> AppBlue
        AppColors.ContainerBackground1 -> AppWhite
        AppColors.ContainerBackground1 -> AppWhite
        AppColors.AppBarBackground -> AppBlack
        AppColors.Tint -> AppRed
        AppColors.Tint2 -> AppWhite
        AppColors.PopupBackground -> AppGray
    }
}
sealed class AppColors{
    data object ScreenBackground: AppColors()
    data object Text1: AppColors()
    data object Text2: AppColors()
    data object Text3: AppColors()
    data object CircleProgress:AppColors()
    data object ContainerBackground1:AppColors()
    data object AppBarBackground:AppColors()
    data object Tint:AppColors()
    data object Tint2:AppColors()
    data object PopupBackground:AppColors()
}
