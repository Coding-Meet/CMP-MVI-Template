package com.example.cmp_mvi_template.feature.pokemon.presentation.favorites.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.delete
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon
import com.example.cmp_mvi_template.feature.pokemon.presentation.component.PokemonCard
import org.jetbrains.compose.resources.stringResource

@Composable
fun SwipeToDeletePokemonCard(
    pokemon: Pokemon,
    onPokemonClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                onDeleteClick()
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = {
            DismissBackground(dismissState = dismissState)
        },
        content = {
            PokemonCard(
                pokemon = pokemon,
                isFavorite = true,
                onPokemonClick = onPokemonClick,
                onFavoriteClick = onDeleteClick
            )
        },
    )
}

@Composable
private fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    val color by animateColorAsState(
        targetValue = when (dismissState.targetValue) {
            SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.error
            else -> MaterialTheme.colorScheme.surface
        },
        label = "dismiss_background_color"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .height(120.dp),
            colors = CardDefaults.cardColors(containerColor = color)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(Res.string.delete),
                    tint = MaterialTheme.colorScheme.onError,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
    }
}

