package com.example.installedapps.features.app_list.ui.mvi

import com.example.installedapps.features.app_list.ui.model.AppListItemUiModel

data class AppListScreenState(
    val installedApps: List<AppListItemUiModel> = emptyList()
)
