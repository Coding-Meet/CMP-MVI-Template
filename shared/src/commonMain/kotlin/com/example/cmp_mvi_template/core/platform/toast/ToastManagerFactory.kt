package com.example.cmp_mvi_template.core.platform.toast


interface ToastManagerFactory {
    fun showToast(message: String, toastDuration: ToastDuration)
}


