package com.example.cmp_mvi_template.feature.product.presentation.product_list.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        FilterChip(
            selected = selectedCategory == null,
            onClick = { onCategorySelected(null) },
            label = { Text("All") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        // Categories from API
        categories.forEach { category ->
            val isSelected = selectedCategory == category.slug
            FilterChip(
                selected = isSelected,
                onClick = { onCategorySelected(category.slug) },
                label = { Text(category.name) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                )
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

