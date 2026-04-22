package com.example.cmp_mvi_template.ui.component.radio_button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview
import com.example.cmp_mvi_template.core.utility.UiText


data class RadioOption(val label: UiText, val enabled: Boolean = true)

@Composable
fun CustomRadioButtonList(
    radioOptionList: List<RadioOption>,
    selectedOption: RadioOption?,
    onClick: (RadioOption) -> Unit,
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    radioOptionList.forEach { item ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = selectedOption == item,
                    role = Role.RadioButton,
                    onClick = {
                        onClick(item)
                    },
                    enabled = item.enabled
                )
        ) {
            RadioButton(
                selected = selectedOption == item,
                onClick = {
                    onClick(item)
                },
                colors = colors,
                enabled = item.enabled
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = item.label.asString())
        }
    }

}

@DefaultPreview
@Composable
fun RadioButtonPreview() {
    ComposeThemePreview {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Single Choice Radio Button", style = MaterialTheme.typography.titleMedium)
                val singleChoiceOptionsList = listOf(
                    RadioOption(UiText.DynamicString("Option 1")),
                    RadioOption(UiText.DynamicString("Option 2")),
                    RadioOption(UiText.DynamicString("Option 3"))
                )
                var selectedOptionText by remember {
                    mutableStateOf<RadioOption?>(/*default selected value or null*/
                        singleChoiceOptionsList[0]
                    )
                }
                CustomRadioButtonList(
                    radioOptionList = singleChoiceOptionsList,
                    selectedOption = selectedOptionText,
                    onClick = {
                        selectedOptionText = it
                    },
                )
                Text("Selected Option: ${selectedOptionText?.label?.asString()}")
            }
        }
    }
}