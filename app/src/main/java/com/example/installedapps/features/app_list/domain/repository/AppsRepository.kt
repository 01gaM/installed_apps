package com.example.installedapps.features.app_list.domain.repository

import com.example.installedapps.features.app_list.domain.model.AppListItem

interface AppsRepository {
    suspend fun getInstalledApps(): List<AppListItem>
}
