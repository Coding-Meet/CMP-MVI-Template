package com.example.cmp_mvi_template.ui.component.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cmp_mvi_template.composeapp.generated.resources.*
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview
import com.example.cmp_mvi_template.core.utility.UiText


@Composable
fun CustomTitleText(
    modifier: Modifier = Modifier,
    title: UiText,
    color: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = title.asString(),
        style = MaterialTheme.typography.headlineMedium,
        color = color,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun CustomSubtitleText(
    modifier: Modifier = Modifier,
    title: UiText,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = title.asString(),
        style = MaterialTheme.typography.titleMedium,
        color = color,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun CustomBodyText(
    modifier: Modifier = Modifier,
    title: UiText,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = title.asString(),
        style = MaterialTheme.typography.bodyMedium,
        color = color,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun CustomCaptionText(
    modifier: Modifier = Modifier,
    title: UiText,
    color: Color = MaterialTheme.colorScheme.secondary,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = title.asString(),
        style = MaterialTheme.typography.labelSmall,
        color = color,
        textAlign = textAlign,
        modifier = modifier
    )
}


@DefaultPreview
@Composable
fun TextPreview() {
    ComposeThemePreview {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomTitleText(
                    title = UiText.StringResource(Res.string.app_name),
                    modifier = Modifier.fillMaxWidth()
                )
                CustomSubtitleText(
                    title = UiText.StringResource(Res.string.app_name),
                    modifier = Modifier.fillMaxWidth()
                )
                CustomBodyText(
                    title = UiText.StringResource(Res.string.app_name),
                    modifier = Modifier.fillMaxWidth()
                )
                CustomCaptionText(
                    title = UiText.StringResource(Res.string.app_name),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
