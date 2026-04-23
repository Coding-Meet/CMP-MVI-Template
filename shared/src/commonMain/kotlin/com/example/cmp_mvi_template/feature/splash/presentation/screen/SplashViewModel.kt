package com.example.cmp_mvi_template.feature.splash.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_mvi_template.core.data.datastore.onboarding.OnboardingPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val onboardingPreferences: OnboardingPreferences,
) : ViewModel() {

    private val _effect = MutableSharedFlow<SplashEffect>()
    val effect = _effect.asSharedFlow()

    private val _startAnimation = MutableStateFlow(false)
    val startAnimation = _startAnimation.asStateFlow()


    init {
        initializeSplash()
    }

    private fun initializeSplash() {
        viewModelScope.launch {
            delay(200)
            _startAnimation.update {
                true
            }
            val isOnboardingCompleted = false
            delay(1000)
            if (isOnboardingCompleted) {
                _effect.emit(SplashEffect.NavigateToMain)
            } else {
                _effect.emit(SplashEffect.NavigateToOnboarding)
            }
        }
    }

}

