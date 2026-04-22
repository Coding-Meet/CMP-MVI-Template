package com.example.cmp_mvi_template.ui.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.app_name
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview
import com.example.cmp_mvi_template.core.utility.UiText


@Composable
fun CustomElevatedButton(
    title: UiText,
    buttonColors: ButtonColors = ButtonDefaults.elevatedButtonColors(),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.elevatedShape,
    elevation: ButtonElevation = ButtonDefaults.elevatedButtonElevation(),
    border: BorderStroke? = null,
    onClick: () -> Unit,
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        colors = buttonColors,
        enabled = enabled,
        shape = shape,
        elevation = elevation,
        border = border
    ) {
        Text(text = title.asString())
    }
}

@Composable
fun CustomFilledTonalButton(
    title: UiText,
    buttonColors: ButtonColors = ButtonDefaults.filledTonalButtonColors(),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.filledTonalShape,
    elevation: ButtonElevation = ButtonDefaults.filledTonalButtonElevation(),
    border: BorderStroke? = null,
    onClick: () -> Unit,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        colors = buttonColors,
        enabled = enabled,
        shape = shape,
        elevation = elevation,
        border = border
    ) {
        Text(text = title.asString())
    }
}

@Composable
fun CustomOutlinedButton(
    title: UiText,
    buttonColors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.outlinedShape,
    elevation: ButtonElevation? = null,
    border: BorderStroke = ButtonDefaults.outlinedButtonBorder(enabled),
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = buttonColors,
        enabled = enabled,
        shape = shape,
        elevation = elevation,
        border = border
    ) {
        Text(text = title.asString())
    }
}

@Composable
fun CustomTextButton(
    title: UiText,
    buttonColors: ButtonColors = ButtonDefaults.textButtonColors(),
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.textShape,
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        colors = buttonColors,
        enabled = enabled,
        shape = shape,
        elevation = elevation,
        border = border
    ) {
        Text(text = title.asString())
    }
}


@DefaultPreview
@Composable
fun ButtonPreview() {
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
                CustomElevatedButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = UiText.StringResource(Res.string.app_name),
                    onClick = {}
                )
                CustomFilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = UiText.StringResource(Res.string.app_name),
                    onClick = {}
                )
                CustomOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = UiText.StringResource(Res.string.app_name),
                    onClick = {}
                )
                CustomTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = UiText.StringResource(Res.string.app_name),
                    onClick = {}
                )
            }
        }
    }
}

