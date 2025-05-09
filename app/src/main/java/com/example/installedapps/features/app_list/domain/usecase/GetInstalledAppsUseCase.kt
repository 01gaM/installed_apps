package com.example.installedapps.features.app_list.domain.usecase

import com.example.installedapps.features.app_list.domain.model.AppListItem
import com.example.installedapps.features.app_list.domain.repository.AppsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetInstalledAppsUseCase(private val appsRepository: AppsRepository) {
    suspend operator fun invoke(): List<AppListItem> = withContext(Dispatchers.IO) {
        appsRepository.getInstalledApps()
    }
}
