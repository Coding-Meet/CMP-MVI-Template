package com.example.cmp_mvi_template.ui.component.checkbox

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.ui.theme.ComposeThemePreview
import com.example.cmp_mvi_template.ui.theme.DefaultPreview
import com.example.cmp_mvi_template.core.utility.UiText


@Composable
fun SingleCheckbox() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = isChecked,
                role = Role.Checkbox,
                onClick = {
                    isChecked = !isChecked
                },
                enabled = true
            )
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = if (isChecked) "Checked" else "Unchecked")
    }
}

data class CheckboxItem(
    val label: UiText,
    val isChecked: Boolean,
    val enabled: Boolean = true,
)

@Composable
fun CustomCheckbox(
    checkboxItems: SnapshotStateList<CheckboxItem>,
    onCheckedChange: (Int, Boolean) -> Unit,
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    checkboxItems.forEachIndexed { index, item ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = item.isChecked,
                    role = Role.Checkbox,
                    onClick = {
                        onCheckedChange(index, !item.isChecked)
                    },
                    enabled = item.enabled
                )
        ) {
            Checkbox(
                checked = item.isChecked,
                onCheckedChange = { checked ->
                    onCheckedChange(index, checked)
                },
                colors = colors,
                enabled = item.enabled
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = item.label.asString())
        }
    }

}

@Composable
fun CustomTriStateCheckbox(
    checkboxItems: SnapshotStateList<CheckboxItem>,
    triStateTitle: UiText,
    triStateChecked: ToggleableState,
    onTriStateCheckedChange: () -> Unit,
    onCheckedChange: (Int, Boolean) -> Unit,
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = triStateChecked == ToggleableState.On,
                role = Role.Checkbox,
                onClick = onTriStateCheckedChange,
            )
            .padding(10.dp)
    ) {
        TriStateCheckbox(
            state = triStateChecked,
            onClick = null,
            colors = colors,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = triStateTitle.asString())
    }
    checkboxItems.forEachIndexed { index, item ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = item.isChecked,
                    role = Role.Checkbox,
                    onClick = {
                        onCheckedChange(index, !item.isChecked)
                    },
                    enabled = item.enabled
                )
        ) {
            Checkbox(
                checked = item.isChecked,
                onCheckedChange = { checked ->
                    onCheckedChange(index, checked)
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
fun CheckBoxPreview() {
    ComposeThemePreview {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Single Checkbox Example", style = MaterialTheme.typography.titleMedium)
                SingleCheckbox()
                val checkboxItems = remember {
                    mutableStateListOf(
                        CheckboxItem(UiText.DynamicString("Item A"), false),
                        CheckboxItem(UiText.DynamicString("Item B"), true),
                        CheckboxItem(UiText.DynamicString("Item C"), false),
                        CheckboxItem(UiText.DynamicString("Item D"), true),
                        CheckboxItem(UiText.DynamicString("Item E"), false),
                    )
                }
                Text(
                    text = "Multiple Checkbox Example",
                    style = MaterialTheme.typography.titleMedium
                )
                CustomCheckbox(
                    checkboxItems = checkboxItems,
                    onCheckedChange = { index, checked ->
                        checkboxItems[index] = checkboxItems[index].copy(isChecked = checked)
                    })
                val checkedList by remember {
                    derivedStateOf {
                        checkboxItems.filter { it.isChecked }
                    }
                }
                val unCheckedList by remember {
                    derivedStateOf {
                        checkboxItems.filter { !it.isChecked }
                    }
                }
                Text(text = "Checked List: $checkedList")
                Text(text = "Unchecked List: $unCheckedList")

                Text(
                    text = "Multiple TriState Checkbox Example",
                    style = MaterialTheme.typography.titleMedium
                )
                var triStateChecked by remember {
                    mutableStateOf(ToggleableState.Indeterminate)
                }

                val checkboxItemsWithTriStateList = remember {
                    mutableStateListOf(
                        CheckboxItem(UiText.DynamicString("Item A"), false),
                        CheckboxItem(UiText.DynamicString("Item B"), true),
                        CheckboxItem(UiText.DynamicString("Item C"), false),
                        CheckboxItem(UiText.DynamicString("Item D"), true),
                        CheckboxItem(UiText.DynamicString("Item E"), false),
                    )
                }
                CustomTriStateCheckbox(
                    checkboxItems = checkboxItemsWithTriStateList,
                    triStateTitle = UiText.DynamicString("Select All"),
                    triStateChecked = triStateChecked,
                    onTriStateCheckedChange = {
                        if (triStateChecked == ToggleableState.Indeterminate) {
                            triStateChecked = ToggleableState.On

                            checkboxItemsWithTriStateList.forEachIndexed { index, it ->
                                val isChecked = it.copy(isChecked = true)
                                checkboxItemsWithTriStateList[index] = isChecked
                            }
                        } else {
                            triStateChecked = ToggleableState.Indeterminate
                        }
                    },
                    onCheckedChange = { index, checked ->
                        checkboxItemsWithTriStateList[index] =
                            checkboxItemsWithTriStateList[index].copy(isChecked = checked)
                        triStateChecked = ToggleableState.Indeterminate
                    }
                )

                val checkedListWithTriState by remember {
                    derivedStateOf {
                        checkboxItemsWithTriStateList.filter { it.isChecked }
                    }
                }
                val unCheckedListWithTriState by remember {
                    derivedStateOf {
                        checkboxItemsWithTriStateList.filter { !it.isChecked }
                    }
                }
                Text(text = "Checked List: $checkedListWithTriState")
                Text(text = "Unchecked List: $unCheckedListWithTriState")
            }

        }
    }
}
