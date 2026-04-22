package com.example.cmp_mvi_template.ui.component.fab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview
import com.example.cmp_mvi_template.core.utility.IconResource
import com.example.cmp_mvi_template.core.utility.UiText


@Composable
fun CustomFloatingActionButton(
    icon: IconResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    shape: Shape = FloatingActionButtonDefaults.shape,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation
    ) {
        Icon(
            icon.asPainterResource(),
            contentDescription = contentDescription
        )
    }
}

@Composable
fun CustomSmallFloatingActionButton(
    icon: IconResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    shape: Shape = FloatingActionButtonDefaults.smallShape,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    onClick: () -> Unit,
) {
    SmallFloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation
    ) {
        Icon(
            icon.asPainterResource(),
            contentDescription = contentDescription
        )
    }
}

@Composable
fun CustomLargeFloatingActionButton(
    icon: IconResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    shape: Shape = FloatingActionButtonDefaults.largeShape,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    onClick: () -> Unit,
) {
    LargeFloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation
    ) {
        Icon(
            icon.asPainterResource(),
            contentDescription = contentDescription
        )
    }
}

@Composable
fun CustomExtendedFloatingActionButton(
    text: UiText,
    icon: IconResource,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = contentColorFor(containerColor),
    expanded: Boolean = true,
    shape: Shape = FloatingActionButtonDefaults.extendedFabShape,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    onClick: () -> Unit,
) {
    ExtendedFloatingActionButton(
        text = { Text(text.asString(), color = contentColor) },
        icon = {
            Icon(
                icon.asPainterResource(),
                contentDescription = contentDescription,
                tint = contentColor
            )
        },
        expanded = expanded,
        shape = shape,
        elevation = elevation,
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor
    )
}

@Composable
fun CustomExtendedFloatingActionButtonWithoutIcon(
    text: UiText,
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    shape: Shape = FloatingActionButtonDefaults.extendedFabShape,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    onClick: () -> Unit,
) {
    ExtendedFloatingActionButton(
        text = { Text(text.asString(), color = contentColor) },
        icon = {},
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
        expanded = expanded,
        shape = shape,
        elevation = elevation
    )
}


@DefaultPreview
@Composable
fun FABPreview() {
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
                CustomFloatingActionButton(
                    icon = IconResource.ImageVector(Icons.Default.Add),
                    contentDescription = "Add",
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { /* Handle click */ }
                )
                CustomSmallFloatingActionButton(
                    icon = IconResource.ImageVector(Icons.Default.Edit),
                    contentDescription = "Edit",
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    onClick = { /* Handle click */ }
                )
                CustomLargeFloatingActionButton(
                    icon = IconResource.ImageVector(Icons.Default.Home),
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary,
                    contentDescription = "Home",
                    onClick = { /* Handle click */ }
                )
                CustomExtendedFloatingActionButton(
                    text = UiText.DynamicString("Send"),
                    icon = IconResource.ImageVector(Icons.AutoMirrored.Filled.Send),
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentDescription = "Send",
                    onClick = { /* Handle click */ }
                )
                CustomExtendedFloatingActionButtonWithoutIcon(
                    text = UiText.DynamicString("More Info"),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    onClick = { /* Handle click */ }
                )
            }
        }
    }

}