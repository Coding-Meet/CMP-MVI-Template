package com.example.cmp_mvi_template.feature.product.presentation.product_list.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.feature.product.domain.entity.ProductCategory
import com.example.cmp_mvi_template.ui.theme.ComponentPreviews

@Composable
fun CategoryChipsRow(
    categories: List<ProductCategory>,
    selectedCategory: String?,
    onCategorySelected: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // "All" chip
        CategoryChip(
            selected = selectedCategory == null,
            onClick = { onCategorySelected(null) },
            label = "All"
        )

        // Categories from API
        categories.forEach { category ->
            val isSelected = selectedCategory == category.slug
            CategoryChip(
                selected = isSelected,
                onClick = { onCategorySelected(category.slug) },
                label = category.name
            )
        }
    }
}

@Composable
private fun CategoryChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val containerColor by animateColorAsState(
        targetValue = when {
            selected -> MaterialTheme.colorScheme.primary
            isHovered -> MaterialTheme.colorScheme.surfaceVariant
            else -> MaterialTheme.colorScheme.surface
        },
        label = "chip_container"
    )

    val contentColor by animateColorAsState(
        targetValue = when {
            selected -> MaterialTheme.colorScheme.onPrimary
            else -> MaterialTheme.colorScheme.onSurface
        },
        label = "chip_content"
    )

    val borderColor by animateColorAsState(
        targetValue = when {
            selected -> Color.Transparent
            isHovered -> MaterialTheme.colorScheme.outline
            else -> MaterialTheme.colorScheme.outlineVariant
        },
        label = "chip_border"
    )

    Surface(
        selected = selected,
        onClick = onClick,
        modifier = modifier.height(32.dp),
        shape = RoundedCornerShape(8.dp),
        color = containerColor,
        border = BorderStroke(1.dp, borderColor),
        interactionSource = interactionSource
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                color = contentColor
            )
        }
    }
}

@ComponentPreviews
@Composable
private fun CategoryChipsRowPreview() {
    CategoryChipsRow(
        categories = listOf(
            ProductCategory(slug = "beauty", name = "Beauty"),
            ProductCategory(slug = "fragrances", name = "Fragrances"),
            ProductCategory(slug = "furniture", name = "Furniture"),
            ProductCategory(slug = "groceries", name = "Groceries")
        ),
        selectedCategory = "beauty",
        onCategorySelected = {}
    )
}
