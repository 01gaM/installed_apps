package com.example.installedapps.features.app_list.data.repository

import android.content.pm.PackageManager
import com.example.installedapps.features.app_info.domain.model.AppInfo
import com.example.installedapps.features.app_list.domain.model.AppListItem
import com.example.installedapps.features.app_list.domain.repository.AppsRepository
import java.io.File
import java.security.MessageDigest
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val packageManager: PackageManager
) : AppsRepository {
    override suspend fun getInstalledApps(): List<AppListItem> {
        val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        return apps.map {
            AppListItem(
                name = packageManager.getApplicationLabel(it).toString(),
                packageName = it.packageName
            )
        }.sortedBy { it.name.lowercase() }
    }

    override suspend fun getAppInfo(packageName: String): AppInfo? {
        return try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            val appInfo = packageInfo.applicationInfo
            appInfo?.let {
                val name = packageManager.getApplicationLabel(appInfo).toString()
                val version = packageInfo.versionName ?: "None"
                val apkPath = appInfo.sourceDir
                val checksum = calculateApkChecksum(apkPath)

                AppInfo(
                    name = name,
                    version = version,
                    packageName = packageName,
                    checkSum = checksum
                )
            } ?: return null
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
    }

    /**
     * Вычисление SHA-1 контрольной суммы (checksum) APK-файла
     */
    private fun calculateApkChecksum(apkPath: String): String {
        val digest = MessageDigest.getInstance("SHA-1")
        File(apkPath).inputStream().use { input ->
            val buffer = ByteArray(8192)
            var bytesRead: Int
            while (input.read(buffer).also { bytesRead = it } > 0) {
                digest.update(buffer, 0, bytesRead)
            }
        }
        return digest.digest().joinToString("") { "%02x".format(it) }
    }
}
