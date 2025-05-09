package com.example.installedapps.features.app_info.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.installedapps.features.app_info.ui.compose_views.AppInfoScreenContent
import com.example.installedapps.features.app_info.ui.mvi.AppInfoScreenViewModel

@Composable
fun AppInfoScreen(navController: NavController) {
    val viewModel: AppInfoScreenViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    AppInfoScreenContent(
        navController = navController,
        state = state,
        onEvent = viewModel::onEvent
    )
}
