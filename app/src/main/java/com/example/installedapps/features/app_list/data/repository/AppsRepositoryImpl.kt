package com.example.installedapps.features.app_list.data.repository

import com.example.installedapps.features.app_list.domain.model.AppListItem
import com.example.installedapps.features.app_list.domain.repository.AppsRepository
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor() : AppsRepository {
    override suspend fun getInstalledApps(): List<AppListItem> {
        // TODO: remove mock
        return listOf(
            AppListItem(
                name = "TestApp1",
                packageName = "com.example.test1"
            ),
            AppListItem(
                name = "TestApp2",
                packageName = "com.example.test2"
            ),
            AppListItem(
                name = "TestApp3",
                packageName = "com.example.test3"
            )
        )
    }
}
