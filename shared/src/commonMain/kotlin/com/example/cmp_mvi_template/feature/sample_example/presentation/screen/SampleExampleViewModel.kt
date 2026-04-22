package com.example.cmp_mvi_template.feature.sample_example.presentation.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_mvi_template.core.platform.toast.ToastDuration
import com.example.cmp_mvi_template.core.utility.StateController
import com.example.cmp_mvi_template.core.utility.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SampleExampleViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow(SampleState())
    val state = _state
        .onStart {
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private val _effect = MutableSharedFlow<SampleEffect>()
    val effect = _effect.asSharedFlow()


    fun handleEvent(event: SampleEvent) {
        when (event) {
            is SampleEvent.NavigateToDetail -> {
                navigateToDetail(event)
            }
            is SampleEvent.NavigateBack -> {
                navigateBack()
            }
            is SampleEvent.LoadingDialogExample -> {
                showLoadingDialogExample()
            }
            is SampleEvent.ShowSystemToast -> {
                showToastMessage(event.message)
            }
            is SampleEvent.ShowPopupToast -> {
                showPopupToastMessage(event.message)
            }
            is SampleEvent.ShowSnackBar -> {
                showSnackBar(event.message)
            }
            is SampleEvent.ShowSnackBarWithAction -> {
                showSnackBarAction(
                    message = event.message
                )
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _effect.emit(SampleEffect.NavigateBack)
        }
    }

    private fun navigateToDetail(event: SampleEvent.NavigateToDetail) {
        viewModelScope.launch {
            _effect.emit(SampleEffect.NavigateToDetails(event.name))
        }
    }
    private fun showToastMessage(message: UiText, duration: ToastDuration = ToastDuration.LONG) {
        viewModelScope.launch {
            StateController.sendSystemToastMsgEvent(Pair(message, duration))
        }
    }
    private fun showPopupToastMessage(message: UiText, duration: ToastDuration = ToastDuration.LONG) {
        viewModelScope.launch {
            StateController.sendPopUpToastMsgEvent(Pair(message, duration))
        }
    }

    private fun showLoadingDialogExample() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            delay(5000)
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    private fun showSnackBar(message: UiText) {
        viewModelScope.launch {
            _effect.emit(SampleEffect.ShowSnackBar(message))
        }
    }
    private fun showSnackBarAction(message: UiText) {
        viewModelScope.launch {
            _effect.emit(SampleEffect.ShowSnackBarWithAction(message))
        }
    }

//    private fun showSnackBar(
//        message: UiText,
//        actionLabel: String? = null,
//        withDismissAction: Boolean = false,
//        duration: SnackbarDuration =
//            if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
//        onDismiss: (() -> Unit)? = null,
//        onAction: (() -> Unit)? = null
//    ) {
//        viewModelScope.launch {
//            val result =
//                snackBarHostState.showSnackbar(
//                    message.asStringForSuspend(),
//                    actionLabel,
//                    withDismissAction,
//                    duration
//                )
//            when (result) {
//                SnackbarResult.Dismissed -> onDismiss?.invoke()
//                SnackbarResult.ActionPerformed -> onAction?.invoke()
//            }
//        }
//    }
}