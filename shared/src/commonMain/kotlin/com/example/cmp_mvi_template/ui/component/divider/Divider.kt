package com.example.cmp_mvi_template.ui.component.divider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview


@Composable
fun HorizontalDividerExample(
    thickness: Dp = 2.dp,
    color: Color = MaterialTheme.colorScheme.secondary
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text("First item in list")
        HorizontalDivider(thickness = thickness, color = color)
        Text("Second item in list")
    }
}

@Composable
fun VerticalDividerExample(
    thickness: Dp = 2.dp,
    color: Color = MaterialTheme.colorScheme.secondary
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("First item in row")
        VerticalDivider(
            thickness = thickness,
            color = color
        )
        Text("Second item in row")
    }
}

@DefaultPreview
@Composable
fun DividerPreview() {

    ComposeThemePreview {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalDividerExample()
                Spacer(modifier = Modifier.height(100.dp))
                VerticalDividerExample()

                Spacer(modifier = Modifier.height(100.dp))
                HorizontalDividerExample(
                    thickness = 4.dp,
                    color = MaterialTheme.colorScheme.errorContainer
                )
                Spacer(modifier = Modifier.height(100.dp))
                VerticalDividerExample(
                    thickness = 4.dp,
                    color = MaterialTheme.colorScheme.errorContainer
                )
            }
        }
    }
}