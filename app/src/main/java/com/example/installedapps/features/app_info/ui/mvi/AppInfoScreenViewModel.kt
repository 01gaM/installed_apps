package com.example.installedapps.features.app_info.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.installedapps.features.app_info.domain.usecase.GetAppInfoUseCase
import com.example.installedapps.features.app_info.ui.mapper.toUiModel
import com.example.installedapps.features.app_info.ui.model.AppInfoUiModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = AppInfoScreenViewModel.AppInfoScreenViewModelFactory::class)
class AppInfoScreenViewModel @AssistedInject constructor(
    @Assisted private val packageName: String,
    private val getAppInfoUseCase: GetAppInfoUseCase
) : ViewModel() {
    private var _uiState = MutableStateFlow(
        AppInfoScreenState(appInfo = AppInfoUiModel(packageName = packageName))
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            bindDataToState()
        }
    }

    @AssistedFactory
    interface AppInfoScreenViewModelFactory {
        fun create(packageName: String): AppInfoScreenViewModel
    }

    fun onEvent(event: AppInfoScreenEvent) {
        when (event) {
            AppInfoScreenEvent.OpenAppButtonClicked -> TODO()
        }
    }

    // region private

    private suspend fun bindDataToState() {
        val appInfo = getAppInfoUseCase(packageName)
        appInfo?.let { app ->
            _uiState.update {
                it.copy(appInfo = app.toUiModel())
            }
        }
    }

    // endregion
}
