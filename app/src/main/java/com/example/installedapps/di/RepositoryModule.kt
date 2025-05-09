package com.example.installedapps.di

import com.example.installedapps.features.app_list.data.repository.AppsRepositoryImpl
import com.example.installedapps.features.app_list.domain.repository.AppsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getAppsRepository(appsRepositoryImpl: AppsRepositoryImpl): AppsRepository
}
