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
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingScreen(onFinished: () -> Unit) {
    val viewModel = koinViewModel<OnboardingViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState(pageCount = { state.pages.size })

    // Drive pager from ViewModel state (button taps / skip)
    LaunchedEffect(state.currentPage) {
        if (pagerState.currentPage != state.currentPage) {
            pagerState.animateScrollToPage(state.currentPage)
        }
    }

    // Sync pager → ViewModel on manual swipes
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage != state.currentPage) {
            viewModel.handleEvent(
                OnboardingEvent.PageChanged(pagerState.currentPage)
            )
        }
    }

    // Navigate away when effect fires
    viewModel.effect.ObserveAsEvents { effect ->
        if (effect is OnboardingEffect.NavigateToMain) onFinished()
    }

    // Per-page background colors — cycles through Material3 container roles
    val containerColors = listOf(
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.primaryContainer,
    )
    val currentContainerColor by animateColorAsState(
        targetValue = containerColors[state.currentPage],
        animationSpec = tween(durationMillis = 400),
    )

    val windowInfo = rememberWindowInfo()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) { paddingValues ->
        if (windowInfo.useHorizontalLayout) {
            ExpandedOnboardingLayout(
                pagerState = pagerState,
                state = state,
                containerColor = currentContainerColor,
                onEvent = viewModel::handleEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            )
        } else {
            CompactOnboardingLayout(
                pagerState = pagerState,
                state = state,
                containerColor = currentContainerColor,
                onEvent = viewModel::handleEvent,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            )
        }
    }
}
