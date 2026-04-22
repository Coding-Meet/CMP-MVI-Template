package com.example.cmp_mvi_template.core.platform.toast

actual fun toastManager(): ToastManagerFactory = AndroidToastManager()