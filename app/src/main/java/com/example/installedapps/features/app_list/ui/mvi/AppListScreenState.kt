package com.example.installedapps.features.app_list.ui.mvi

import com.example.installedapps.features.app_list.ui.model.AppListItem

data class AppListScreenState(
    val installedApps: List<AppListItem> = emptyList()
)
