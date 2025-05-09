package com.example.installedapps.features.app_info.domain.usecase

import com.example.installedapps.features.app_info.domain.model.AppInfo
import com.example.installedapps.features.app_list.domain.repository.AppsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAppInfoUseCase(private val appsRepository: AppsRepository) {
    suspend operator fun invoke(packageName: String): AppInfo? = withContext(Dispatchers.IO) {
        appsRepository.getAppInfo(packageName = packageName)
    }
}
