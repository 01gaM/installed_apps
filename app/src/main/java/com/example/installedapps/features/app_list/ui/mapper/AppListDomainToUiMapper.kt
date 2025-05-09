package com.example.installedapps.features.app_list.ui.mapper

import com.example.installedapps.features.app_list.domain.model.AppListItem
import com.example.installedapps.features.app_list.ui.model.AppListItemUiModel

fun AppListItem.toUiModel() = AppListItemUiModel(
    name = this.name,
    packageName = this.packageName
)
