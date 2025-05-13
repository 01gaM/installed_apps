package com.example.installedapps.di

import android.content.Context
import android.content.pm.PackageManager
import com.example.installedapps.features.app_list.data.repository.AppsRepositoryImpl
import com.example.installedapps.features.app_list.domain.repository.AppsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun providePackageManager(
        @ApplicationContext context: Context
    ): PackageManager {
        return context.packageManager
    }

    @Provides
    fun getAppsRepository(packageManager: PackageManager): AppsRepository {
        return AppsRepositoryImpl(packageManager)
    }
}
