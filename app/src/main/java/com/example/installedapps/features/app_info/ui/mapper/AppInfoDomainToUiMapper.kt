package com.example.installedapps.features.app_info.ui.mapper

import com.example.installedapps.features.app_info.domain.model.AppInfo
import com.example.installedapps.features.app_info.ui.model.AppInfoUiModel

fun AppInfo.toUiModel() = AppInfoUiModel(
    name = this.name,
    version = this.version,
    packageName = this.packageName,
    checkSum = this.checkSum
)
