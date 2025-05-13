package com.example.installedapps.features.app_list.ui.compose_views

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.example.installedapps.R
import com.example.installedapps.features.app_list.ui.model.AppListItemUiModel
import com.example.installedapps.features.app_list.ui.mvi.AppListScreenEvent
import com.example.installedapps.common.ui.theme.InstalledAppsTheme
import com.example.installedapps.features.app_list.ui.mvi.AppListScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreenContent(
    modifier: Modifier = Modifier,
    state: AppListScreenState,
    onEvent: (AppListScreenEvent) -> Unit
) {
    var appIconsList by remember { mutableStateOf(emptyList<BitmapPainter?>()) }
    LaunchedEffect(key1 = state.installedApps) {
        if (state.installedApps.isNotEmpty()) {
            appIconsList = withContext(Dispatchers.Default) {
                getAppIcons(installedApps = state.installedApps)
            }
            onEvent(AppListScreenEvent.AppIconsLoaded)
        }
    }

    Scaffold(
        modifier = modifier,
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
        if (state.isLoading) {
            LoaderPlaceholder(modifier = Modifier.padding(paddingValues = paddingValues))
        } else {
            if (state.installedApps.isEmpty()) {
                EmptyListPlaceholder(modifier = Modifier.padding(paddingValues = paddingValues))
            } else {
                AppListContent(
                    modifier = Modifier.padding(paddingValues = paddingValues),
                    installedApps = state.installedApps,
                    appIconsList = appIconsList,
                    onItemClick = { appListItem ->
                        onEvent(
                            AppListScreenEvent.AppListItemClicked(appListItem.packageName)
                        )
                    }
                )
            }
        }
    }
}

// region private

private fun getAppIcons(installedApps: List<AppListItemUiModel>): List<BitmapPainter?> {
    return installedApps.map { app ->
        app.appIcon?.let {
            BitmapPainter(it.toBitmap().asImageBitmap())
        }
    }
}

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
private fun LoaderPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun AppListContent(
    modifier: Modifier = Modifier,
    installedApps: List<AppListItemUiModel>,
    appIconsList: List<BitmapPainter?>,
    onItemClick: (AppListItemUiModel) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        itemsIndexed(
            items = installedApps,
            key = { _, item -> item.packageName }
        ) { index, appListItem ->
            AppListItem(
                appListItem = appListItem,
                appIconBitmapPainter = appIconsList.getOrNull(index),
                onClick = { onItemClick(appListItem) }
            )
        }
    }
}

@Composable
private fun AppListItem(
    modifier: Modifier = Modifier,
    appListItem: AppListItemUiModel,
    appIconBitmapPainter: BitmapPainter?,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (appIconBitmapPainter == null) {
            Icon(
                modifier = Modifier
                    .size(size = 48.dp)
                    .padding(end = 16.dp),
                painter = painterResource(R.drawable.apk_document_24px),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        } else {
            Image(
                modifier = Modifier
                    .size(size = 48.dp)
                    .padding(end = 16.dp),
                painter = appIconBitmapPainter,
                contentDescription = null
            )
        }

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
