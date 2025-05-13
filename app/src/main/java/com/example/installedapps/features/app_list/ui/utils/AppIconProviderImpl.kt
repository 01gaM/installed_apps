package com.example.installedapps.features.app_list.ui.utils

import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import javax.inject.Inject

class AppIconProviderImpl @Inject constructor(
    private val packageManager: PackageManager
) : AppIconProvider {

    override fun getAppIcon(packageName: String): Drawable? {
        return try {
            packageManager.getApplicationIcon(packageName)
        } catch (e: Exception) {
            null
        }
    }
}
