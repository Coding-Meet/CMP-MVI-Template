package com.example.cmp_mvi_template.ui.component.slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview


@DefaultPreview
@Composable
fun SliderPreview() {

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

                var sliderPosition by remember { mutableFloatStateOf(0f) }

                Slider(
                    value = sliderPosition,
                    modifier = Modifier.fillMaxWidth(0.7f),
                    onValueChange = { sliderPosition = it },
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.secondary,
                        activeTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                )
                Text(text = sliderPosition.toString())


                var sliderValuePosition by remember { mutableFloatStateOf(0f) }
                Slider(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    value = sliderValuePosition,
                    onValueChange = { sliderValuePosition = it },
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.secondary,
                        activeTrackColor = MaterialTheme.colorScheme.secondary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                    ),
                    steps = 3,
                    valueRange = 0f..50f
                )
                Text(text = sliderValuePosition.toString())

                var rangeSliderPosition by remember { mutableStateOf(0f..100f) }
                RangeSlider(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    value = rangeSliderPosition,
                    steps = 5,
                    onValueChange = { range -> rangeSliderPosition = range },
                    valueRange = 0f..100f,
                    onValueChangeFinished = {
                        // launch some business logic update with the state you hold
                        // viewModel.updateSelectedSliderValue(sliderPosition)
                    },
                )
                Text(text = rangeSliderPosition.toString())
            }

        }
    }
}
