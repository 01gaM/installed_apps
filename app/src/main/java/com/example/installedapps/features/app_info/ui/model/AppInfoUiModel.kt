package com.example.installedapps.features.app_info.ui.model

data class AppInfoUiModel(
    val name: String = "",
    val version: String = "",
    val packageName: String = "",
    val checkSum: String = "" // Контрольная сумма apk-файла
)
