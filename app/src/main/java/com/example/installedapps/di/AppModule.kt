package com.example.installedapps.di

import android.content.Context
import android.content.pm.PackageManager
import com.example.installedapps.features.app_list.ui.utils.AppIconProvider
import com.example.installedapps.features.app_list.ui.utils.AppIconProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun providePackageManager(
        @ApplicationContext context: Context
    ): PackageManager {
        return context.packageManager
    }

    @Provides
    fun provideAppIconProvider(
        packageManager: PackageManager
    ): AppIconProvider {
        return AppIconProviderImpl(packageManager)
    }
}
