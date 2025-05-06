package com.example.installedapps.features.app_list.ui.mvi

sealed class AppListScreenEvent {
    data class AppListItemClicked(val packageName: String): AppListScreenEvent()
}
