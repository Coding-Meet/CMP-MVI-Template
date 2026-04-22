package com.example.cmp_mvi_template.feature.pokemon.presentation.pokemon_details.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon

@Composable
fun PokemonDetailsContent(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PokemonImageSection(pokemon = pokemon)
        }

        item {
            PokemonBasicInfoSection(pokemon = pokemon)
        }

        item {
            PokemonStatsSection(pokemon = pokemon)
        }

        item {
            PokemonAbilitiesSection(pokemon = pokemon)
        }
    }
}