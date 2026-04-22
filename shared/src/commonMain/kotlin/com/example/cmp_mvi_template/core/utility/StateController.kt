package com.example.cmp_mvi_template.core.utility

import com.example.cmp_mvi_template.core.platform.toast.ToastDuration
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object StateController {

    private val _systemToastEvent = MutableSharedFlow<Pair<UiText?, ToastDuration>?>()
    val systemToastEventFlow = _systemToastEvent.asSharedFlow()

    suspend fun sendSystemToastMsgEvent(event: Pair<UiText?, ToastDuration>?) {
        _systemToastEvent.emit(event)
    }

    private val _popUpToastEvent = MutableSharedFlow<Pair<UiText?, ToastDuration>?>()
    val popUpToastEventFlow = _popUpToastEvent.asSharedFlow()

    suspend fun sendPopUpToastMsgEvent(event: Pair<UiText?, ToastDuration>?) {
        _popUpToastEvent.emit(event)
    }
}