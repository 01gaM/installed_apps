package com.example.installedapps.features.app_list.ui.compose_views

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.installedapps.R
import com.example.installedapps.features.app_list.ui.model.AppListItemUiModel
import com.example.installedapps.features.app_list.ui.mvi.AppListScreenEvent
import com.example.installedapps.common.ui.theme.InstalledAppsTheme
import com.example.installedapps.features.app_list.ui.mvi.AppListScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreenContent(
    modifier: Modifier = Modifier,
    state: AppListScreenState,
    onEvent: (AppListScreenEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.appListScreen_title),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors()
                    .copy(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { paddingValues ->
        if (state.installedApps.isEmpty()) {
            EmptyListPlaceholder(modifier = Modifier.padding(paddingValues = paddingValues))
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(paddingValues = paddingValues)
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                items(
                    items = state.installedApps,
                    key = { item -> item.packageName }
                ) { appListItem ->
                    AppListItem(
                        appListItem = appListItem,
                        onClick = {
                            onEvent(AppListScreenEvent.AppListItemClicked(appListItem.packageName))
                        }
                    )
                }
            }
        }
    }
}

// region private

@Composable
private fun EmptyListPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.appListScreen_emptyState),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun AppListItem(
    modifier: Modifier = Modifier,
    appListItem: AppListItemUiModel,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(size = 48.dp)
                .padding(end = 16.dp),
            painter = painterResource(R.drawable.apk_document_24px),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )

        Text(
            text = appListItem.name,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

// endregion

// region preview

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AppsListScreenContentPreview() {
    InstalledAppsTheme {
        AppListScreenContent(
            state = AppListScreenState(
                installedApps = listOf(
                    AppListItemUiModel(
                        name = "name1",
                        packageName = "com.example.name1"
                    ),
                    AppListItemUiModel(
                        name = "name2",
                        packageName = "com.example.name2"
                    )
                )

            ),
            onEvent = {}
        )
    }
}

// endregion
