package com.example.installedapps.features.app_info.ui.compose_views

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.installedapps.R
import com.example.installedapps.common.ui.theme.InstalledAppsTheme
import com.example.installedapps.features.app_info.ui.model.AppInfoUiModel
import com.example.installedapps.features.app_info.ui.mvi.AppInfoScreenEvent
import com.example.installedapps.features.app_info.ui.mvi.AppInfoScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppInfoScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    state: AppInfoScreenState,
    onEvent: (AppInfoScreenEvent) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.appInfoScreen_title),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() },
                        content = {
                            Icon(
                                modifier = Modifier.padding(all = 8.dp),
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
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
            AppDescription(appInfo = state.appInfo)

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                onClick = { onEvent(AppInfoScreenEvent.OpenAppButtonClicked) },
                content = {
                    Text(text = stringResource(R.string.appInfoScreen_openAppButton).uppercase())
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
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "$title: ",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = description,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun AppDescription(appInfo: AppInfoUiModel) {
    val fields = listOf(
        R.string.appInfoScreen_descriptionField_name to appInfo.name,
        R.string.appInfoScreen_descriptionField_version to appInfo.version,
        R.string.appInfoScreen_descriptionField_packageName to appInfo.packageName,
        R.string.appInfoScreen_descriptionField_checkSum to appInfo.checkSum,
    )

    fields.forEach { (titleRes, description) ->
        AppDescriptionField(
            title = stringResource(titleRes),
            description = description
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
        AppInfoScreenContent(
            state = AppInfoScreenState(
                appInfo = AppInfoUiModel(
                    name = "MyAppName",
                    version = "1.2.0",
                    packageName = "com.example.test",
                    checkSum = "checksum"
                )
            ),
            navController = rememberNavController(),
            onEvent = {}
        )
    }
}

// endregion
