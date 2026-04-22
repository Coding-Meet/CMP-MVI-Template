package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.base_stats
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon
import org.jetbrains.compose.resources.stringResource

@Composable
fun PokemonStatsSection(pokemon: Pokemon) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(Res.string.base_stats),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            pokemon.stats.forEach { stat ->
                StatBar(
                    statName = stat.stat.name.replace("-", " ").replaceFirstChar { it.uppercase() },
                    statValue = stat.baseStat,
                    maxValue = 255
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun StatBar(
    statName: String,
    statValue: Int,
    maxValue: Int
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = statName,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = statValue.toString(),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        LinearProgressIndicator(
            progress = { statValue.toFloat() / maxValue },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = getStatColor(statValue),
        )
    }
}

@Composable
private fun getStatColor(statValue: Int): Color {
    return when {
        statValue >= 120 -> Color(0xFF4CAF50) // Green
        statValue >= 80 -> Color(0xFFFF9800) // Orange
        statValue >= 50 -> Color(0xFFFFC107) // Amber
        else -> Color(0xFFF44336) // Red
    }
}