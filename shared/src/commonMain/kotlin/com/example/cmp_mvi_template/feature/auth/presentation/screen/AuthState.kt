package com.example.cmp_mvi_template.feature.auth.presentation.screen

import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.feature.auth.domain.entity.AuthUser

data class AuthState(
    val isLoggedIn: Boolean = false,
    val user: AuthUser? = null,
    val usernameInput: String = "",
    val passwordInput: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val showLogoutDialog: Boolean = false,
    val error: UiText? = null
)
