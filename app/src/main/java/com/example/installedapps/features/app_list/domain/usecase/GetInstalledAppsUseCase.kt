package com.example.installedapps.features.app_list.domain.usecase

import com.example.installedapps.features.app_list.domain.model.AppInfo
import com.example.installedapps.features.app_list.domain.repository.AppsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetInstalledAppsUseCase(private val appsRepository: AppsRepository) {
    suspend operator fun invoke(): List<AppInfo> = withContext(Dispatchers.IO) {
        appsRepository.getInstalledApps()
    }
}
