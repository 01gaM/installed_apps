package com.example.installedapps.features.app_list.data.repository

import com.example.installedapps.features.app_list.domain.model.AppInfo
import com.example.installedapps.features.app_list.domain.repository.AppsRepository
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor() : AppsRepository {
    override suspend fun getInstalledApps(): List<AppInfo> {
        // TODO: remove mock
        return listOf(
            AppInfo(
                name = "TestApp1",
                packageName = "com.example.test1"
            ),
            AppInfo(
                name = "TestApp2",
                packageName = "com.example.test2"
            ),
            AppInfo(
                name = "TestApp3",
                packageName = "com.example.test3"
            )
        )
    }
}
