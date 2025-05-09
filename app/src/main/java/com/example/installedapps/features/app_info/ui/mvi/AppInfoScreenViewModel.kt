package com.example.installedapps.features.app_info.ui.mvi

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel(assistedFactory = AppInfoScreenViewModel.AppInfoScreenViewModelFactory::class)
class AppInfoScreenViewModel @AssistedInject constructor(
    @Assisted private val packageName: String
) : ViewModel() {
    private var _uiState = MutableStateFlow(AppInfoScreenState(packageName = packageName))
    val uiState = _uiState.asStateFlow()

    @AssistedFactory
    interface AppInfoScreenViewModelFactory {
        fun create(packageName: String): AppInfoScreenViewModel
    }

    fun onEvent(event: AppInfoScreenEvent) {
        when (event) {
            AppInfoScreenEvent.OpenAppButtonClicked -> TODO()
        }
    }
}
