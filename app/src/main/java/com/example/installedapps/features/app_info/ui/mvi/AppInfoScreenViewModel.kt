package com.example.installedapps.features.app_info.ui.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppInfoScreenViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(AppInfoScreenState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AppInfoScreenEvent) {
        when (event) {
            AppInfoScreenEvent.OpenAppClicked -> TODO()
        }
    }
}
