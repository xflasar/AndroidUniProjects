package com.example.destillery.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(private val navController: NavController) : INavigationRouter {
    override fun navigateToAddScreen() {
        navController.navigate(Destination.AddScreen.route)
    }

    override fun navigateToStatisticsScreen() {
        navController.navigate(Destination.StatisticsScreen.route)
    }

    override fun returnBack() {
        navController.popBackStack()
    }
}