package com.example.installedapps.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.installedapps.features.app_info.ui.AppInfoScreen
import com.example.installedapps.features.app_list.ui.AppListScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "apps") {
        composable("app_list") {
//            AppListScreen()
        }
        composable("app_info") {
//            AppInfoScreen()
        }
    }
}
