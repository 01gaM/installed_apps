package com.example.installedapps.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.installedapps.R
import com.example.installedapps.features.app_info.ui.AppInfoScreen
import com.example.installedapps.features.app_list.ui.AppListScreen

@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current

    NavHost(navController, startDestination = Screen.AppList.route) {
        composable(route = Screen.AppList.route) {
            AppListScreen(
                onNavigateToAppInfo = { packageName ->
                    navController.navigate("${Screen.AppInfo.route}/$packageName")
                }
            )
        }

        composable(
            route = "${Screen.AppInfo.route}/{packageName}",
            arguments = listOf(
                navArgument("packageName") {
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("packageName")?.let { packageName ->
                AppInfoScreen(
                    navController = navController,
                    packageName = packageName,
                    onOpenApp = {
                        launchApp(
                            context = context,
                            packageName = packageName
                        )
                    }
                )
            }
        }
    }
}

// region private

private fun launchApp(
    context: Context,
    packageName: String
) {
    val packageManager = context.packageManager
    val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
    if (launchIntent != null) {
        context.startActivity(launchIntent)
    } else {
        Toast.makeText(
            context,
            context.getString(R.string.appInfoScreen_launchErrorToast),
            Toast.LENGTH_SHORT
        ).show()
    }
}

// endregion
