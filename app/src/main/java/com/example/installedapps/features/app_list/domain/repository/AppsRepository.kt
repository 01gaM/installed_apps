package com.example.installedapps.features.app_list.domain.repository

import com.example.installedapps.features.app_info.domain.model.AppInfo
import com.example.installedapps.features.app_list.domain.model.AppListItem

interface AppsRepository {
    suspend fun getInstalledApps(): List<AppListItem>
    suspend fun getAppInfo(packageName: String): AppInfo?
}
