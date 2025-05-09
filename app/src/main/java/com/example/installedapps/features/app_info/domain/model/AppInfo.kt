package com.example.installedapps.features.app_info.domain.model

data class AppInfo(
    val name: String = "", // название приложения
    val version: String = "", // версия
    val packageName: String = "", // имя пакета приложения
    val checkSum: String = "" // контрольная сумма apk-файла
)
