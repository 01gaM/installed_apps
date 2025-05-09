package com.example.installedapps.features.app_list.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.installedapps.features.app_list.domain.usecase.GetInstalledAppsUseCase
import com.example.installedapps.features.app_list.ui.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListScreenViewModel @Inject constructor(
    private val getInstalledAppsUseCase: GetInstalledAppsUseCase
) : ViewModel() {
    private var _uiState = MutableStateFlow(AppListScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            bindDataToState()
        }
    }

    fun onEvent(event: AppListScreenEvent) {
        when (event) {
            is AppListScreenEvent.AppListItemClicked -> TODO()
        }
    }

    // region private

    private suspend fun bindDataToState() {
        val installedAppsList = getInstalledAppsUseCase().map { it.toUiModel() }
        _uiState.update {
            it.copy(installedApps = installedAppsList)
        }
    }

    // endregion
}
