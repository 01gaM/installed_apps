package com.example.installedapps.features.app_info.ui.mvi

sealed class AppInfoScreenEvent {
    data object OpenAppButtonClicked: AppInfoScreenEvent()
}
