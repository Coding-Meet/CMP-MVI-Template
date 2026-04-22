package com.example.cmp_mvi_template.ui.component.segmented_button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SegmentedButtonDefaults.ActiveIcon
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview
import com.example.cmp_mvi_template.core.utility.IconResource
import com.example.cmp_mvi_template.core.utility.UiText


data class OptionWithIcon(
    val title: UiText,
    val iconResource: IconResource,
)

data class Option(val title: UiText)

@Composable
fun CustomSingleChoiceTextSegmentedButton(
    options: List<Option>,
    selectedOption: Option? = null,
    onOptionSelected: (Option) -> Unit,
    rowModifier: Modifier = Modifier,
    colors: SegmentedButtonColors = SegmentedButtonDefaults.colors(),
    shape: Shape? = null,
    eachButtonModifier: Modifier = Modifier,
    space: Dp = SegmentedButtonDefaults.BorderWidth,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = rowModifier,
        space = space
    ) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                modifier = eachButtonModifier,
                shape = shape ?: SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = {
                    onOptionSelected(option)
                },
                colors = colors,
                selected = selectedOption == option,
            ) {
                Text(option.title.asString())
            }
        }
    }
}

@Composable
fun CustomSingleChoiceSegmentedButtonWithIcon(
    options: List<OptionWithIcon>,
    selectedOption: OptionWithIcon?,
    onOptionSelected: (OptionWithIcon) -> Unit,
    rowModifier: Modifier = Modifier,
    colors: SegmentedButtonColors = SegmentedButtonDefaults.colors(),
    shape: Shape? = null,
    eachButtonModifier: Modifier = Modifier,
    space: Dp = SegmentedButtonDefaults.BorderWidth,
) {
    SingleChoiceSegmentedButtonRow(
        modifier = rowModifier,
        space = space
    ) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                modifier = eachButtonModifier,
                shape = shape ?: SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = {
                    onOptionSelected(option)
                },
                colors = colors,
                selected = selectedOption == option,
                label = {
                    Text(option.title.asString())
                },
                icon = {
                    SegmentedButtonDefaults.Icon(
                        active = selectedOption == option,
                        activeContent = { ActiveIcon() },
                        inactiveContent = {
                            Icon(
                                option.iconResource.asPainterResource(),
                                contentDescription = null,
                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                            )
                        }
                    )
                }
            )
        }
    }
}

@Composable
fun CustomMultiChoiceTextSegmentedButton(
    options: List<Option>,
    checkedOptionList: SnapshotStateList<Option>,
    onOptionSelected: (Option) -> Unit,
    rowModifier: Modifier = Modifier,
    colors: SegmentedButtonColors = SegmentedButtonDefaults.colors(),
    shape: Shape? = null,
    eachButtonModifier: Modifier = Modifier,
    space: Dp = SegmentedButtonDefaults.BorderWidth,
) {
    MultiChoiceSegmentedButtonRow(
        modifier = rowModifier,
        space = space
    ) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                modifier = eachButtonModifier,
                shape = shape ?: SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onCheckedChange = {
                    onOptionSelected(option)
                },
                colors = colors,
                checked = option in checkedOptionList
            ) {
                Text(option.title.asString())
            }
        }
    }
}

@Composable
fun CustomMultiChoiceSegmentedButtonWithIcon(
    options: List<OptionWithIcon>,
    checkedOptionList: SnapshotStateList<OptionWithIcon>,
    onOptionSelected: (OptionWithIcon) -> Unit,
    rowModifier: Modifier = Modifier,
    colors: SegmentedButtonColors = SegmentedButtonDefaults.colors(),
    shape: Shape? = null,
    eachButtonModifier: Modifier = Modifier,
    space: Dp = SegmentedButtonDefaults.BorderWidth,
) {
    MultiChoiceSegmentedButtonRow(
        modifier = rowModifier,
        space = space
    ) {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                modifier = eachButtonModifier,
                shape = shape ?: SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onCheckedChange = {
                    onOptionSelected(option)
                },
                colors = colors,
                checked = option in checkedOptionList,
                label = {
                    Text(option.title.asString())
                },
                icon = {
                    SegmentedButtonDefaults.Icon(
                        active = option in checkedOptionList,
                        activeContent = { ActiveIcon() },
                        inactiveContent = {
                            Icon(
                                option.iconResource.asPainterResource(),
                                contentDescription = null,
                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                            )
                        }
                    )
                }
            )
        }
    }
}

//Use for simple choices between two to five items (for more items or complex choices, use chips)
@DefaultPreview
@Composable
fun SegmentedButtonPreview() {
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
                Text("Single Choice Segmented Button", style = MaterialTheme.typography.titleMedium)
                val singleChoiceOptionsList = listOf(
                    Option(UiText.DynamicString("Option 1")),
                    Option(UiText.DynamicString("Option 2")),
                    Option(UiText.DynamicString("Option 3"))
                )
                var selectedOptionText by remember {
                    mutableStateOf<Option?>(/*default selected value or null*/
                        singleChoiceOptionsList[0]
                    )
                }
                CustomSingleChoiceTextSegmentedButton(
                    options = singleChoiceOptionsList,
                    selectedOption = selectedOptionText,
                    onOptionSelected = {
                        selectedOptionText = it
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                )
                CustomSingleChoiceTextSegmentedButton(
                    options = singleChoiceOptionsList,
                    selectedOption = selectedOptionText,
                    onOptionSelected = {
                        selectedOptionText = it
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10),
                )
                CustomSingleChoiceTextSegmentedButton(
                    options = singleChoiceOptionsList,
                    selectedOption = selectedOptionText,
                    onOptionSelected = {
                        selectedOptionText = it
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    eachButtonModifier = Modifier.padding(horizontal = 4.dp)
                )

                val singleChoiceOptionsIconList = listOf(
                    OptionWithIcon(
                        title = UiText.DynamicString("Option 1"),
                        iconResource = IconResource.ImageVector(Icons.Default.Home)
                    ),
                    OptionWithIcon(
                        title = UiText.DynamicString("Option 2"),
                        iconResource = IconResource.ImageVector(Icons.Default.Person)
                    ),
                    OptionWithIcon(
                        title = UiText.DynamicString("Option 3"),
                        iconResource = IconResource.ImageVector(Icons.Default.Settings)
                    )
                )
                var selectedOptionIcon by remember {
                    mutableStateOf<OptionWithIcon?>( /*default selected value or null*/
                        singleChoiceOptionsIconList[0]
                    )
                }
                CustomSingleChoiceSegmentedButtonWithIcon(
                    options = singleChoiceOptionsIconList,
                    selectedOption = selectedOptionIcon,
                    onOptionSelected = {
                        selectedOptionIcon = it
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                )
                CustomSingleChoiceSegmentedButtonWithIcon(
                    options = singleChoiceOptionsIconList,
                    selectedOption = selectedOptionIcon,
                    onOptionSelected = {
                        selectedOptionIcon = it
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10)
                )
                CustomSingleChoiceSegmentedButtonWithIcon(
                    options = singleChoiceOptionsIconList,
                    selectedOption = selectedOptionIcon,
                    onOptionSelected = {
                        selectedOptionIcon = it
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    eachButtonModifier = Modifier.padding(horizontal = 4.dp)
                )
                Text("Multi Choice Segmented Button", style = MaterialTheme.typography.titleMedium)
                val multiChoiceOptionsList = listOf(
                    Option(UiText.DynamicString("Option 1")),
                    Option(UiText.DynamicString("Option 2")),
                    Option(UiText.DynamicString("Option 3")),
                    Option(UiText.DynamicString("Option 4")),
                )

                val checkedOptionList = remember {
                    mutableStateListOf(
                        /*default selected value list or nothing just specify type in mutableStateListOf*/
                        multiChoiceOptionsList[0], multiChoiceOptionsList[3]
                    )
                }
                CustomMultiChoiceTextSegmentedButton(
                    options = multiChoiceOptionsList,
                    checkedOptionList = checkedOptionList,
                    onOptionSelected = { selectedOption ->
                        if (selectedOption in checkedOptionList) {
                            checkedOptionList.remove(selectedOption)
                        } else {
                            checkedOptionList.add(selectedOption)
                        }
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                )
                CustomMultiChoiceTextSegmentedButton(
                    options = multiChoiceOptionsList,
                    checkedOptionList = checkedOptionList,
                    onOptionSelected = { selectedOption ->
                        if (selectedOption in checkedOptionList) {
                            checkedOptionList.remove(selectedOption)
                        } else {
                            checkedOptionList.add(selectedOption)
                        }
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10)
                )
                CustomMultiChoiceTextSegmentedButton(
                    options = multiChoiceOptionsList,
                    checkedOptionList = checkedOptionList,
                    onOptionSelected = { selectedOption ->
                        if (selectedOption in checkedOptionList) {
                            checkedOptionList.remove(selectedOption)
                        } else {
                            checkedOptionList.add(selectedOption)
                        }
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    eachButtonModifier = Modifier.padding(horizontal = 4.dp)
                )


                val multiChoiceOptionsWithIconList = listOf(
                    OptionWithIcon(
                        UiText.DynamicString("Option 1"),
                        IconResource.ImageVector(Icons.Default.Home)
                    ),
                    OptionWithIcon(
                        UiText.DynamicString("Option 2"),
                        IconResource.ImageVector(Icons.Default.Settings)
                    ),
                    OptionWithIcon(
                        UiText.DynamicString("Option 3"),
                        IconResource.ImageVector(Icons.Default.ShoppingCart)
                    ),
                    OptionWithIcon(
                        UiText.DynamicString("Option 4"),
                        IconResource.ImageVector(Icons.Default.Person)
                    ),
                )

                val checkedOptionWithIconList = remember {
                    mutableStateListOf(
                        /*default selected value list or nothing just specify type in mutableStateListOf*/
                        multiChoiceOptionsWithIconList[0], multiChoiceOptionsWithIconList[3]
                    )
                }


                CustomMultiChoiceSegmentedButtonWithIcon(
                    options = multiChoiceOptionsWithIconList,
                    checkedOptionList = checkedOptionWithIconList,
                    onOptionSelected = { selectedOption ->
                        if (selectedOption in checkedOptionWithIconList) {
                            checkedOptionWithIconList.remove(selectedOption)
                        } else {
                            checkedOptionWithIconList.add(selectedOption)
                        }
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                )
                CustomMultiChoiceSegmentedButtonWithIcon(
                    options = multiChoiceOptionsWithIconList,
                    checkedOptionList = checkedOptionWithIconList,
                    onOptionSelected = { selectedOption ->
                        if (selectedOption in checkedOptionWithIconList) {
                            checkedOptionWithIconList.remove(selectedOption)
                        } else {
                            checkedOptionWithIconList.add(selectedOption)
                        }
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10)
                )
                CustomMultiChoiceSegmentedButtonWithIcon(
                    options = multiChoiceOptionsWithIconList,
                    checkedOptionList = checkedOptionWithIconList,
                    onOptionSelected = { selectedOption ->
                        if (selectedOption in checkedOptionWithIconList) {
                            checkedOptionWithIconList.remove(selectedOption)
                        } else {
                            checkedOptionWithIconList.add(selectedOption)
                        }
                    },
                    rowModifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    eachButtonModifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }
}