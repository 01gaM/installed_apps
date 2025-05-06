package com.example.installedapps.features.app_list.ui.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppListScreenViewModel: ViewModel() {
    private var _uiState = MutableStateFlow(AppListScreenState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AppListScreenEvent) {
        when (event) {
            is AppListScreenEvent.AppListItemClicked -> TODO()
        }
    }
}
