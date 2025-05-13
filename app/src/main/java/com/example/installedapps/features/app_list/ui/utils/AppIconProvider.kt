package com.example.installedapps.features.app_list.ui.utils

import android.graphics.drawable.Drawable

interface AppIconProvider {
    fun getAppIcon(packageName: String): Drawable?
}
