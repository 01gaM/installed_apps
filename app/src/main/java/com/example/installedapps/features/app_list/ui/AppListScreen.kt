package com.example.installedapps.features.app_list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.installedapps.features.app_list.ui.compose_views.AppListScreenContent
import com.example.installedapps.features.app_list.ui.mvi.AppListScreenEffect
import com.example.installedapps.features.app_list.ui.mvi.AppListScreenViewModel

@Composable
fun AppListScreen(onNavigateToAppInfo: (String) -> Unit) {
    val viewModel: AppListScreenViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    AppListScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.effectFlow.collect { effect ->
            handleEffect(
                effect = effect,
                onNavigateToAppInfo = onNavigateToAppInfo
            )
        }
    }
}

// region private

private fun handleEffect(
    effect: AppListScreenEffect,
    onNavigateToAppInfo: (String) -> Unit
) {
    when (effect) {
        is AppListScreenEffect.OpenAppInfoScreen -> {
            onNavigateToAppInfo(effect.packageName)
        }
    }
}

// endregion
