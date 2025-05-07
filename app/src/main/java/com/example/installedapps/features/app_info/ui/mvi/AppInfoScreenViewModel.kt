package com.example.installedapps.features.app_info.ui.mvi

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AppInfoScreenViewModel @Inject constructor() : ViewModel() {
    private var _uiState = MutableStateFlow(AppInfoScreenState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AppInfoScreenEvent) {
        when (event) {
            AppInfoScreenEvent.OpenAppButtonClicked -> TODO()
        }
    }
}
