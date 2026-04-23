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
            OnboardingEvent.NextPage -> nextPage()
            OnboardingEvent.PreviousPage -> previousPage()
            is OnboardingEvent.PageChanged -> onPageChanged(event.page)
            OnboardingEvent.CompleteOnboarding,
            OnboardingEvent.SkipOnboarding -> complete()
        }
    }

    private fun nextPage() {
        _state.update {
            it.copy(currentPage = (it.currentPage + 1).coerceAtMost(it.pages.lastIndex))
        }
    }

    private fun previousPage() {
        _state.update {
            it.copy(currentPage = (it.currentPage - 1).coerceAtLeast(0))
        }
    }

    private fun onPageChanged(page: Int) {
        _state.update { it.copy(currentPage = page) }
    }

    private fun complete() {
        viewModelScope.launch {
            onboardingPreferences.setOnboardingCompleted()
            _effect.emit(OnboardingEffect.NavigateToMain)
        }
    }
}
