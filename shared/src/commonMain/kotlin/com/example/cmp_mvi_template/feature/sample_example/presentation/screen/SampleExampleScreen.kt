package com.example.cmp_mvi_template.feature.sample_example.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cmp_mvi_template.core.utility.ObserveAsEvents
import com.example.cmp_mvi_template.core.utility.UiText
import com.example.cmp_mvi_template.ui.dialog.LoadingDialog
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SampleExampleScreen(
    onNavigateBack: () -> Unit,
    onNavigateToDetails: (String) -> Unit
) {
    val sampleExampleViewModel = koinViewModel<SampleExampleViewModel>()
    val state by sampleExampleViewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }


    // Handle effects
    sampleExampleViewModel.effect.ObserveAsEvents { effect ->
        when (effect) {
            is SampleEffect.NavigateBack -> {
                onNavigateBack()
            }

            is SampleEffect.NavigateToDetails -> {
                onNavigateToDetails(effect.name)
            }

            is SampleEffect.ShowSnackBar -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }

            is SampleEffect.ShowSnackBarWithAction -> {
                val result = snackBarHostState.showSnackbar(
                    message = effect.message.asStringForSuspend(),
                    actionLabel = "Undo"
                )
                when (result) {
                    SnackbarResult.Dismissed -> {
                        // snackBar dismissed
                    }
                    SnackbarResult.ActionPerformed -> {
                        // snackBar action performed
                    }
                }
            }

            is SampleEffect.ShowError -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }

            is SampleEffect.ShowSuccess -> {
                snackBarHostState.showSnackbar(effect.message.asStringForSuspend())
            }
        }
    }


    LoadingDialog(
        isLoadingState = state.isLoading
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                sampleExampleViewModel.handleEvent(
                    SampleEvent.NavigateToDetail("CMP-MVI-Template")
                )
            }) {
                Text("Move Detail Screen")
            }
            Button(onClick = {
                sampleExampleViewModel.handleEvent(
                    SampleEvent.LoadingDialogExample
                )
            }) {
                Text("Show Loading")
            }
            Button(
                onClick = {
                    sampleExampleViewModel.handleEvent(
                        SampleEvent.ShowSystemToast(
                            UiText.DynamicString("Show System Toast")
                        )
                    )
                }) {
                Text("Show System Toast")
            }
            Button(
                onClick = {
                    sampleExampleViewModel.handleEvent(
                        SampleEvent.ShowPopupToast(
                            UiText.DynamicString("Show Popup Toast")
                        )
                    )
                }) {
                Text("Show Popup Toast")
            }
            Button(
                onClick = {
                    sampleExampleViewModel.handleEvent(
                        SampleEvent.ShowSnackBar(
                            message = UiText.DynamicString("Show SnackBar")
                        )
                    )
                }
            ) {
                Text("Show SnackBar")
            }
            Button(
                onClick = {
                    sampleExampleViewModel.handleEvent(
                        SampleEvent.ShowSnackBarWithAction(
                            message = UiText.DynamicString("Show SnackBar with Action")
                        )
                    )
                }) {
                Text("Show SnackBar with Action")
            }
        }
    }
}