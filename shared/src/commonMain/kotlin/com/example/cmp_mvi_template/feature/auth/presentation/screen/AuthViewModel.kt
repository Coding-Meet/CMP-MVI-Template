package com.example.cmp_mvi_template.feature.auth.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_mvi_template.core.domain.onError
import com.example.cmp_mvi_template.core.domain.onSuccess
import com.example.cmp_mvi_template.core.domain.toUiText
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.auth.domain.usecase.GetAuthStateUseCase
import com.example.cmp_mvi_template.feature.auth.domain.usecase.LoginUseCase
import com.example.cmp_mvi_template.feature.auth.domain.usecase.LogoutUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val getAuthStateUseCase: GetAuthStateUseCase,
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state
        .onStart {
            observeAuthState()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private val _effect = MutableSharedFlow<AuthEffect>()
    val effect = _effect.asSharedFlow()

    private fun observeAuthState() {
        viewModelScope.launch {
            combine(
                getAuthStateUseCase.isLoggedIn,
                getAuthStateUseCase.authUser
            ) { isLoggedIn, user ->
                Pair(isLoggedIn, user)
            }.collect { (isLoggedIn, user) ->
                _state.update {
                    it.copy(
                        isLoggedIn = isLoggedIn,
                        user = user
                    )
                }
            }
        }
    }

    fun handleEvent(event: AuthEvent) {
        when (event) {
            AuthEvent.LoadAuthState -> observeAuthState()
            is AuthEvent.UpdateUsername -> _state.update { it.copy(usernameInput = event.value, error = null) }
            is AuthEvent.UpdatePassword -> _state.update { it.copy(passwordInput = event.value, error = null) }
            AuthEvent.TogglePasswordVisibility -> _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            AuthEvent.FillDemoCredentials -> {
                _state.update {
                    it.copy(
                        usernameInput = "emilys",
                        passwordInput = "emilyspass",
                        error = null
                    )
                }
            }
            AuthEvent.Login -> login()
            AuthEvent.Logout -> logout()
            is AuthEvent.ToggleLogoutDialog -> _state.update { it.copy(showLogoutDialog = event.show) }
            AuthEvent.ClearError -> _state.update { it.copy(error = null) }
        }
    }

    private fun login() {
        val username = _state.value.usernameInput
        val password = _state.value.passwordInput

        if (username.isBlank() || password.isBlank()) {
            _state.update {
                it.copy(error = UiText.DynamicString("Please enter both username and password"))
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            loginUseCase(username, password)
                .onSuccess { user ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            usernameInput = "",
                            passwordInput = "",
                            error = null
                        )
                    }
                    _effect.emit(
                        AuthEffect.ShowSuccess(
                            UiText.DynamicString("Welcome back, ${user.firstName}!")
                        )
                    )
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = error.toUiText()
                        )
                    }
                    _effect.emit(AuthEffect.ShowError(error.toUiText()))
                }
        }
    }

    private fun logout() {
        viewModelScope.launch {
            _state.update { it.copy(showLogoutDialog = false) }
            logoutUseCase()
            _effect.emit(AuthEffect.ShowSuccess(UiText.DynamicString("Signed out successfully")))
        }
    }
}

