package com.example.cmp_mvi_template.feature.onboarding.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import cmp_mvi_template.composeapp.generated.resources.Res
import cmp_mvi_template.composeapp.generated.resources.onboarding_get_started
import cmp_mvi_template.composeapp.generated.resources.onboarding_next
import cmp_mvi_template.composeapp.generated.resources.onboarding_skip
import com.example.cmp_mvi_template.feature.onboarding.presentation.model.OnboardingPage
import com.example.cmp_mvi_template.feature.onboarding.presentation.screen.OnboardingEvent
import com.example.cmp_mvi_template.feature.onboarding.presentation.screen.OnboardingState
import org.jetbrains.compose.resources.stringResource
import kotlin.math.absoluteValue

// ── Compact (phone) — vertical layout ──────────────────────────────────────

@Composable
fun CompactOnboardingLayout(
    pagerState: PagerState,
    state: OnboardingState,
    containerColor: Color,
    onEvent: (OnboardingEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Icon pager — top 55% of screen
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.55f),
        ) { page ->

            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).absoluteValue

            val scale = lerp(0.85f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
            val alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
            Box(
                modifier = Modifier.graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                }
            ) {
                PageIconSection(
                    page = state.pages[page],
                    containerColor = containerColor,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }

        // Text + controls — bottom 45%
        // Box lets the text float centered while controls stay pinned to the bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.45f)
                .padding(horizontal = 32.dp),
        ) {
            PageTextSection(
                page = state.pages[state.currentPage],
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(bottom = 80.dp),
            )
            ControlsSection(
                state = state,
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
            )
        }
    }
}

// ── Expanded (tablet / desktop) — horizontal layout ────────────────────────

@Composable
fun ExpandedOnboardingLayout(
    pagerState: PagerState,
    state: OnboardingState,
    containerColor: Color,
    onEvent: (OnboardingEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        // Left — icon pager
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f),
        ) { page ->

            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                    ).absoluteValue

            val scale = lerp(0.85f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
            val alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
            Box(
                modifier = Modifier.graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                }
            ) {
                PageIconSection(
                    page = state.pages[page],
                    containerColor = containerColor,
                    modifier = Modifier.fillMaxSize(),
                    iconSize = 200.dp,
                    circleSize = 300.dp,
                )
            }
        }

        // Right — text + controls
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
                .padding(vertical = 64.dp, horizontal = 48.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(Modifier.height(32.dp))
            PageTextSection(
                page = state.pages[state.currentPage],
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )
            ControlsSection(
                state = state,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

// ── Shared sub-composables ──────────────────────────────────────────────────

@Composable
private fun PageIconSection(
    page: OnboardingPage,
    containerColor: Color,
    modifier: Modifier = Modifier,
    iconSize: Dp = 96.dp,
    circleSize: Dp = 180.dp,
) {
    Box(
        modifier = modifier.background(containerColor),
        contentAlignment = Alignment.Center,
    ) {
        // Soft circle behind icon
        Box(
            modifier = Modifier
                .size(circleSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.25f)),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = page.icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Composable
private fun PageTextSection(
    page: OnboardingPage,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = if (textAlign == TextAlign.Center) Alignment.CenterHorizontally else Alignment.Start,
    ) {
        Text(
            text = stringResource(page.titleRes),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            textAlign = textAlign,
            color = MaterialTheme.colorScheme.onBackground,
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(page.descRes),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = textAlign,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ControlsSection(
    state: OnboardingState,
    onEvent: (OnboardingEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        OnboardingPageIndicator(
            pageCount = state.pages.size,
            currentPage = state.currentPage,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Skip — hidden on last page
            AnimatedVisibility(
                visible = !state.isLastPage,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically(),
            ) {
                TextButton(onClick = { onEvent(OnboardingEvent.SkipOnboarding) }) {
                    Text(
                        text = stringResource(Res.string.onboarding_skip),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            // Spacer to push button right when skip is hidden
            if (state.isLastPage) Spacer(Modifier.width(1.dp))

            // Next / Get Started
            Button(
                onClick = {
                    if (state.isLastPage) onEvent(OnboardingEvent.CompleteOnboarding)
                    else onEvent(OnboardingEvent.NextPage)
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
            ) {
                Text(
                    text = if (state.isLastPage) stringResource(Res.string.onboarding_get_started)
                    else stringResource(Res.string.onboarding_next),
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                )
            }
        }
    }
}
