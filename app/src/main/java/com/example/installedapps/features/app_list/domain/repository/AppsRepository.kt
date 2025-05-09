package com.example.installedapps.features.app_list.domain.repository

import com.example.installedapps.features.app_list.domain.model.AppInfo

interface AppsRepository {
    suspend fun getInstalledApps(): List<AppInfo>
}
