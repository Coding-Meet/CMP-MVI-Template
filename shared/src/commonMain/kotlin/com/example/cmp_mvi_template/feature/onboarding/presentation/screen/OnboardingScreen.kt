package com.example.cmp_mvi_template.feature.onboarding.presentation.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.core.utility.rememberWindowInfo
import com.example.cmp_mvi_template.feature.onboarding.presentation.component.CompactOnboardingLayout
import com.example.cmp_mvi_template.feature.onboarding.presentation.component.ExpandedOnboardingLayout
import com.example.cmp_mvi_template.ui.theme.AllAdaptivePreviews
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    val viewModel = koinViewModel<OnboardingViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Navigate away when effect fires
    viewModel.effect.ObserveAsEvents { effect ->
        if (effect is OnboardingEffect.NavigateToMain) onFinished()
    }

    OnboardingContent(
        state = state,
        onEvent = viewModel::handleEvent
    )
}

@Composable
fun OnboardingContent(
    state: OnboardingState,
    onEvent: (OnboardingEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { state.pages.size })

    // Drive pager from state (button taps / skip)
    LaunchedEffect(state.currentPage) {
        if (pagerState.currentPage != state.currentPage) {
            pagerState.animateScrollToPage(state.currentPage)
        }
    }

    // Sync pager on manual swipes
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage != state.currentPage) {
            onEvent(OnboardingEvent.PageChanged(pagerState.currentPage))
        }
    }

    // Per-page background colors — cycles through Material3 container roles
    val containerColors = listOf(
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.primaryContainer,
    )
    val currentContainerColor by animateColorAsState(
        targetValue = containerColors.getOrElse(state.currentPage) { MaterialTheme.colorScheme.primaryContainer },
        animationSpec = tween(durationMillis = 400),
    )

    val windowInfo = rememberWindowInfo()

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) { paddingValues ->
        if (windowInfo.useHorizontalLayout) {
            ExpandedOnboardingLayout(
                pagerState = pagerState,
                state = state,
                containerColor = currentContainerColor,
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            )
        } else {
            CompactOnboardingLayout(
                pagerState = pagerState,
                state = state,
                containerColor = currentContainerColor,
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            )
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun OnboardingScreenPreview() {
    OnboardingContent(
        state = OnboardingState(),
        onEvent = {}
    )
}
