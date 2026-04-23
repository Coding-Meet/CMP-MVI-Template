package com.example.cmp_mvi_template.feature.splash.presentation.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.app_name
import cmp_mvi_template.composeapp.generated.resources.splash_tagline
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(
    onNavigateToOnboarding: () -> Unit,
    onNavigateToMain: () -> Unit,
) {
    val splashViewModel = koinViewModel<SplashViewModel>()
    val startAnimation by splashViewModel.startAnimation.collectAsStateWithLifecycle()

    val transition = updateTransition(
        targetState = startAnimation,
        label = "splash_transition"
    )

    val scale by transition.animateFloat(
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMediumLow
            )
        },
        label = "scale"
    ) { if (it) 1f else 0f }

    val alpha by transition.animateFloat(
        transitionSpec = { tween(500) },
        label = "alpha"
    ) { if (it) 1f else 0f }

    val titleOffset by transition.animateFloat(
        transitionSpec = { tween(600, delayMillis = 200) },
        label = "titleOffset"
    ) { if (it) 0f else 40f }

    val taglineAlpha by transition.animateFloat(
        transitionSpec = { tween(500, delayMillis = 400) },
        label = "taglineAlpha"
    ) { if (it) 1f else 0f }

    // Handle effects
    splashViewModel.effect.ObserveAsEvents { effect ->
        when (effect) {
            SplashEffect.NavigateToMain -> onNavigateToMain()
            SplashEffect.NavigateToOnboarding -> onNavigateToOnboarding()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f),
                        MaterialTheme.colorScheme.background,
                    ),
                    radius = 1400f,
                )
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // Gradient circle with spring-bounce entrance
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            ),
                        )
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.AutoAwesome,
                    contentDescription = null,
                    modifier = Modifier
                        .size(96.dp),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }

            Spacer(Modifier.height(36.dp))

            // App name — slides up + fades in
            Text(
                text = stringResource(Res.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.graphicsLayer {
                    this.alpha = alpha
                    translationY = titleOffset
                },
            )

            Spacer(Modifier.height(8.dp))

            // Tagline
            Text(
                text = stringResource(Res.string.splash_tagline),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.graphicsLayer { this.alpha = taglineAlpha },
            )
        }
    }
}

