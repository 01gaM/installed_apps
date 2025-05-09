package com.example.installedapps.features.app_list.ui.mvi

sealed class AppListScreenEffect {
    data class OpenAppInfoScreen(val packageName: String): AppListScreenEffect()
}
