package com.example.destillery.navigation

sealed class Destination(val route: String) {
    object MainScreen : Destination("main_screen")
    object AddScreen : Destination("add_screen")
    object StatisticsScreen : Destination("statistics_screen")
}