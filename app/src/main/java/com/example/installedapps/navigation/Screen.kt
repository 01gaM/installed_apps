package com.example.installedapps.navigation

sealed class Screen(val route: String) {
    data object AppList : Screen("appList")
    data object AppInfo : Screen("appInfo")
}
