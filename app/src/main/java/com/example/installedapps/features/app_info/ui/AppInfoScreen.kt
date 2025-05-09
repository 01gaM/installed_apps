package com.example.installedapps.features.app_info.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.installedapps.features.app_info.ui.compose_views.AppInfoScreenContent
import com.example.installedapps.features.app_info.ui.mvi.AppInfoScreenEffect
import com.example.installedapps.features.app_info.ui.mvi.AppInfoScreenViewModel

@Composable
fun AppInfoScreen(
    navController: NavController,
    packageName: String
) {
    val viewModel: AppInfoScreenViewModel = hiltViewModel(
        creationCallback = { factory: AppInfoScreenViewModel.AppInfoScreenViewModelFactory ->
            factory.create(packageName)
        }
    )
    val state by viewModel.uiState.collectAsState()

    AppInfoScreenContent(
        navController = navController,
        state = state,
        onEvent = viewModel::onEvent
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.effectFlow.collect { effect ->
            handleEffect(effect)
        }
    }
}

// region private

private fun handleEffect(effect: AppInfoScreenEffect) {
    when (effect) {
        is AppInfoScreenEffect.OpenApp -> {
            // TODO
        }
    }
}

// endregion
