package com.example.installedapps.features.app_list.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.installedapps.features.app_list.domain.usecase.GetInstalledAppsUseCase
import com.example.installedapps.features.app_list.ui.mapper.toUiModel
import com.example.installedapps.features.app_list.ui.utils.AppIconProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AppListScreenViewModel @Inject constructor(
    private val getInstalledAppsUseCase: GetInstalledAppsUseCase,
    private val appIconProvider: AppIconProvider
) : ViewModel() {
    private var _uiState = MutableStateFlow(AppListScreenState())
    val uiState = _uiState.asStateFlow()

    private var _effectFlow: MutableSharedFlow<AppListScreenEffect> = MutableSharedFlow()
    val effectFlow = _effectFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            bindDataToState()
        }
    }

    fun onEvent(event: AppListScreenEvent) {
        when (event) {
            is AppListScreenEvent.AppListItemClicked -> viewModelScope.launch {
                _effectFlow.emit(AppListScreenEffect.OpenAppInfoScreen(event.packageName))
            }

            AppListScreenEvent.AppIconsLoaded -> _uiState.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    // region private

    private suspend fun bindDataToState() {
        _uiState.update {
            it.copy(isLoading = true)
        }

        val installedAppsList = withContext(Dispatchers.IO) {
            getInstalledAppsUseCase().map {
                it.toUiModel().copy(
                    appIcon = appIconProvider.getAppIcon(it.packageName)
                )
            }
        }

        _uiState.update {
            it.copy(installedApps = installedAppsList)
        }
    }

    // endregion
}
