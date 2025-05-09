package com.example.installedapps.features.app_list.ui.mapper

import com.example.installedapps.features.app_list.domain.model.AppInfo
import com.example.installedapps.features.app_list.ui.model.AppListItem

fun AppInfo.toUiModel() = AppListItem(
    name = this.name,
    packageName = this.packageName
)
