package com.example.cmp_mvi_template.feature.auth.presentation.screen

sealed interface AuthEvent {
    data object LoadAuthState : AuthEvent
    data class UpdateUsername(val value: String) : AuthEvent
    data class UpdatePassword(val value: String) : AuthEvent
    data object TogglePasswordVisibility : AuthEvent
    data object FillDemoCredentials : AuthEvent
    data object Login : AuthEvent
    data object Logout : AuthEvent
    data class ToggleLogoutDialog(val show: Boolean) : AuthEvent
    data object ClearError : AuthEvent
}
