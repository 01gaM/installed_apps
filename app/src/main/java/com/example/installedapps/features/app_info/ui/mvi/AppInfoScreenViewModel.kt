package com.example.installedapps.features.app_info.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.installedapps.features.app_info.ui.model.AppInfoUiModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = AppInfoScreenViewModel.AppInfoScreenViewModelFactory::class)
class AppInfoScreenViewModel @AssistedInject constructor(
    @Assisted private val packageName: String
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

    private fun bindDataToState() {
        // TODO
    }

    // endregion
}
