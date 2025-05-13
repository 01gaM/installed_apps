package com.example.installedapps.di

import android.content.pm.PackageManager
import com.example.installedapps.features.app_list.data.repository.AppsRepositoryImpl
import com.example.installedapps.features.app_list.domain.repository.AppsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun getAppsRepository(packageManager: PackageManager): AppsRepository {
        return AppsRepositoryImpl(packageManager)
    }
}
