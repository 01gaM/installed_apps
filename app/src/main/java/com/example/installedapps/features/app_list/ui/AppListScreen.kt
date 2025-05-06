package com.example.installedapps.features.app_list.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.installedapps.features.app_list.ui.compose_views.AppListScreenContent
import com.example.installedapps.features.app_list.ui.mvi.AppListScreenViewModel

@Composable
fun AppListScreen(viewModel: AppListScreenViewModel) {
    //val viewModel: AppListScreenViewModel = hiltViewModel() // TODO
    val state by viewModel.uiState.collectAsState()

    AppListScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}
