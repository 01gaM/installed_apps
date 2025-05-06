package com.example.installedapps.features.app_info.ui.mvi

data class AppInfoScreenState(
    val name: String = "",
    val version: String = "",
    val packageName: String = "",
    val checkSum: String = "" // Контрольная сумма apk-файла
)
