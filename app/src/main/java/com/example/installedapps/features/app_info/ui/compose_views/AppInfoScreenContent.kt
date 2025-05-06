package com.example.installedapps.features.app_info.ui.compose_views

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.installedapps.common.ui.theme.InstalledAppsTheme
import com.example.installedapps.features.app_info.ui.mvi.AppInfoScreenEvent
import com.example.installedapps.features.app_info.ui.mvi.AppInfoScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppInfoScreenContent(
    modifier: Modifier = Modifier,
    state: AppInfoScreenState,
    onEvent: (AppInfoScreenEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Информация о приложении",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.padding(all = 8.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors()
                    .copy(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { contentPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues = contentPadding)
                .padding(all = 16.dp)
        ) {
            AppDescription(appInfo = state)

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                onClick = { onEvent(AppInfoScreenEvent.OpenAppClicked) },
                content = {
                    Text(text = "Открыть".uppercase())
                }
            )
        }
    }
}

// region private

@Composable
private fun AppDescriptionField(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "$title: ",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = description,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun AppDescription(appInfo: AppInfoScreenState) {
    with(appInfo) {
        AppDescriptionField(title = "Название", description = name)
        AppDescriptionField(title = "Версия", description = version)
        AppDescriptionField(title = "Имя пакета", description = packageName)
        AppDescriptionField(title = "Контрольная сумма", description = checkSum)
    }
}

// endregion

// region preview

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AppsListScreenContentPreview() {
    InstalledAppsTheme {
        AppInfoScreenContent(
            state = AppInfoScreenState(
                name = "MyAppName",
                version = "1.2.0",
                packageName = "com.example.test",
                checkSum = "checksum"
            ),
            onEvent = {}
        )
    }
}

// endregion
