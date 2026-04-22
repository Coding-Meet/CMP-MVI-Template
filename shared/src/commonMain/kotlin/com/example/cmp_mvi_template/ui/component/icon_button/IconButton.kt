package com.example.cmp_mvi_template.ui.component.icon_button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.app_name
import cmp_mvi_template.composeapp.generated.resources.compose_multiplatform
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview
import com.example.cmp_mvi_template.core.utility.IconResource
import com.example.cmp_mvi_template.core.utility.UiText


@Composable
fun CustomFilledIconButton(
    icon: IconResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    shape: Shape = IconButtonDefaults.filledShape,
    colors: IconButtonColors = IconButtonDefaults.filledIconButtonColors(),
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    FilledIconButton(
        onClick = onClick,
        colors = colors,
        shape = shape,
        enabled = enabled,
        modifier = modifier
    ) {
        Icon(
            icon.asPainterResource(),
            contentDescription = contentDescription,
        )
    }
}

@Composable
fun CustomOutlinedIconButton(
    icon: IconResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = IconButtonDefaults.outlinedShape,
    colors: IconButtonColors = IconButtonDefaults.outlinedIconButtonColors(),
    border: BorderStroke? = IconButtonDefaults.outlinedIconButtonBorder(enabled),
    onClick: () -> Unit,
) {
    OutlinedIconButton(
        onClick = onClick,
        shape = shape,
        colors = colors,
        enabled = enabled,
        border = border,
        modifier = modifier
    ) {
        Icon(
            icon.asPainterResource(),
            contentDescription = contentDescription,
        )
    }
}

@Composable
fun CustomIconButton(
    icon: IconResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    iconButtonColors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        colors = iconButtonColors,
        enabled = enabled,
        modifier = modifier
    ) {
        Icon(
            icon.asPainterResource(),
            contentDescription = contentDescription,
        )
    }
}

@Composable
fun CustomElevatedButtonWithIcon(
    title: UiText,
    icon: IconResource,
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
        Icon(
            icon.asPainterResource(),
            contentDescription = title.asString(),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title.asString())
    }
}


@Composable
fun CustomFilledTonalButtonWithIcon(
    title: UiText,
    icon: IconResource,
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
        Icon(
            icon.asPainterResource(),
            contentDescription = title.asString(),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title.asString())
    }
}

@Composable
fun CustomOutlinedButtonWithIcon(
    title: UiText,
    icon: IconResource,
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
        Icon(
            icon.asPainterResource(),
            contentDescription = title.asString(),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title.asString())
    }
}

@Composable
fun CustomTextButtonWithIcon(
    title: UiText,
    icon: IconResource,
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
        Icon(
            icon.asPainterResource(),
            contentDescription = title.asString(),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = title.asString())
    }
}

@DefaultPreview
@Composable
fun IconButtonPreview() {
    ComposeThemePreview {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(12.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CustomFilledIconButton(
                    modifier = Modifier,
                    icon = IconResource.DrawableResource(Res.drawable.compose_multiplatform),
                    onClick = {}
                )
                CustomOutlinedIconButton(
                    modifier = Modifier,
                    icon = IconResource.DrawableResource(Res.drawable.compose_multiplatform),
                    onClick = {}
                )
                CustomIconButton(
                    modifier = Modifier,
                    icon = IconResource.DrawableResource(Res.drawable.compose_multiplatform),
                    onClick = {}
                )
                CustomIconButton(
                    modifier = Modifier,
                    icon = IconResource.DrawableResource(Res.drawable.compose_multiplatform),
                    iconButtonColors = IconButtonDefaults.filledTonalIconButtonColors(),
                    onClick = {}
                )
                CustomElevatedButtonWithIcon(
                    modifier = Modifier.fillMaxWidth(),
                    title = UiText.StringResource(Res.string.app_name),
                    icon = IconResource.ImageVector(Icons.Filled.Email),
                    onClick = {}
                )
                CustomFilledTonalButtonWithIcon(
                    modifier = Modifier.fillMaxWidth(),
                    title = UiText.StringResource(Res.string.app_name),
                    icon = IconResource.DrawableResource(Res.drawable.compose_multiplatform),
                    onClick = {}
                )
                CustomOutlinedButtonWithIcon(
                    modifier = Modifier.fillMaxWidth(),
                    title = UiText.StringResource(Res.string.app_name),
                    icon = IconResource.ImageVector(Icons.Filled.Email),
                    onClick = {}
                )
                CustomTextButtonWithIcon(
                    modifier = Modifier.fillMaxWidth(),
                    title = UiText.StringResource(Res.string.app_name),
                    icon = IconResource.DrawableResource(Res.drawable.compose_multiplatform),
                    onClick = {}
                )
            }
        }
    }
}
