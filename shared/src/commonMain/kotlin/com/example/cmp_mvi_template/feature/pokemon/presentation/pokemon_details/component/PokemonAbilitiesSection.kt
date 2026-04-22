package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cmp_mvi_template.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon

@Composable
fun PokemonAbilitiesSection(pokemon: Pokemon) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(Res.string.abilities),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pokemon.abilities) { ability ->
                    AbilityChip(
                        abilityName = ability.ability.name,
                        isHidden = ability.isHidden
                    )
                }
            }
        }
    }
}

@Composable
private fun AbilityChip(
    abilityName: String,
    isHidden: Boolean
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = if (isHidden) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = abilityName.replace("-", " ").replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )

            if (isHidden) {
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(Res.string.hidden_label),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}