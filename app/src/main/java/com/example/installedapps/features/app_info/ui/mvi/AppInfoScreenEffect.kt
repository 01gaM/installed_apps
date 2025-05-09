package com.example.installedapps.features.app_info.ui.mvi

sealed class AppInfoScreenEffect {
    data object OpenApp : AppInfoScreenEffect()
}
