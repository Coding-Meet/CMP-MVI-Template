package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon
import cmp_mvi_template.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun PokemonBasicInfoSection(pokemon: Pokemon) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(Res.string.basic_information),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                BasicInfoItem(
                    label = stringResource(Res.string.height),
                    value = "${pokemon.height / 10.0} m",
                    modifier = Modifier.weight(1f)
                )

                BasicInfoItem(
                    label = stringResource(Res.string.weight),
                    value = "${pokemon.weight / 10.0} kg",
                    modifier = Modifier.weight(1f)
                )

                BasicInfoItem(
                    label = stringResource(Res.string.base_xp),
                    value = pokemon.baseExperience.toString(),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun BasicInfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

