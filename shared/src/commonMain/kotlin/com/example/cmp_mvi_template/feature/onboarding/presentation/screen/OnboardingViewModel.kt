package com.example.cmp_mvi_template.feature.onboarding.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_mvi_template.core.data.datastore.onboarding.OnboardingPreferences
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val onboardingPreferences: OnboardingPreferences,
) : ViewModel() {

    private val _effect = MutableSharedFlow<OnboardingEffect>()
    val effect = _effect.asSharedFlow()

    private val _state = MutableStateFlow(OnboardingState())
    val state = _state.asStateFlow()

    fun handleEvent(event: OnboardingEvent) {
        when (event) {
            OnboardingEvent.NextPage -> _state.update {
                it.copy(currentPage = (it.currentPage + 1).coerceAtMost(it.pages.lastIndex))
            }

            OnboardingEvent.PreviousPage -> _state.update {
                it.copy(currentPage = (it.currentPage - 1).coerceAtLeast(0))
            }

            is OnboardingEvent.PageChanged -> {
                _state.update { it.copy(currentPage = event.page) }
            }

            OnboardingEvent.CompleteOnboarding,
            OnboardingEvent.SkipOnboarding -> complete()
        }
    }

    private fun complete() {
        viewModelScope.launch {
            onboardingPreferences.setOnboardingCompleted()
            _effect.emit(OnboardingEffect.NavigateToMain)
        }
    }
}
