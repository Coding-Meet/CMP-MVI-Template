package com.example.cmp_mvi_template.core.platform.toast

import android.content.Context
import android.widget.Toast
import org.koin.mp.KoinPlatform

internal class AndroidToastManager : ToastManagerFactory {
    override fun showToast(message: String, toastDuration: ToastDuration) {
        val context = KoinPlatform.getKoin().get<Context>()
        val duration = when (toastDuration) {
            ToastDuration.SHORT -> Toast.LENGTH_SHORT
            ToastDuration.LONG -> Toast.LENGTH_LONG
        }
        Toast.makeText(context, message, duration).show()
    }
}