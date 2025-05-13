package com.example.installedapps.features.app_list.ui.model

import android.graphics.drawable.Drawable

data class AppListItemUiModel(
    val name: String = "",
    val packageName: String = "",
    val appIcon: Drawable? = null
)
