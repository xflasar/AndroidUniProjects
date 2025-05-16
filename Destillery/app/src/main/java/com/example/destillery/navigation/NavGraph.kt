package com.example.destillery.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.destillery.ui.screens.AddScreen
import com.example.destillery.ui.screens.MainScreen
import com.example.destillery.ui.screens.StatisticsScreen

@Composable
fun NavGraph(startNavigation: String, navHostController: NavHostController = rememberNavController(), navRouter: INavigationRouter = remember {
    NavigationRouterImpl(navHostController)
}) {
    NavHost(navController = navHostController, startDestination = startNavigation) {
        composable(route = Destination.MainScreen.route){ MainScreen(navRouter) }
        composable(route = Destination.AddScreen.route) { AddScreen(navRouter) }
        composable(route = Destination.StatisticsScreen.route) { StatisticsScreen() }
    }
}