package com.example.installedapps.di

import com.example.installedapps.features.app_list.domain.repository.AppsRepository
import com.example.installedapps.features.app_list.domain.usecase.GetInstalledAppsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun getGetInstalledAppsUseCase(appsRepository: AppsRepository): GetInstalledAppsUseCase {
        return GetInstalledAppsUseCase(appsRepository = appsRepository)
    }
}
