package com.example.cmp_mvi_template.core.platform.toast

import androidx.compose.ui.awt.ComposeWindow
import java.awt.Color
import javax.swing.BorderFactory
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JWindow
import javax.swing.Timer

internal class DesktopToastManager : ToastManagerFactory {
    override fun showToast(message: String, toastDuration: ToastDuration) {
        val parent = composeWindowProvider.invoke()
        val durationType = when (toastDuration) {
            ToastDuration.SHORT -> 2000
            ToastDuration.LONG -> 5000
        }
        if (parent != null) {
            val toast = JWindow(parent)
            toast.background = Color(0, 0, 0, 0) // Transparent window

            val panel = RoundedPanel(arcWidth = 28, arcHeight = 28)
            val label = JLabel(message)
            label.foreground = Color.WHITE
            label.background = Color.BLACK
            label.isOpaque = false
            label.border = BorderFactory.createEmptyBorder(10, 24, 10, 24)
            panel.add(label)
            toast.contentPane.add(panel)
            toast.pack()

            // Position at bottom center inside the parent window
            val parentBounds = parent.bounds
            val x = parentBounds.x + (parentBounds.width - toast.width) / 2
            val y = parentBounds.y + parentBounds.height - toast.height - 40
            toast.setLocation(x, y)
            toast.isVisible = true

            Timer(durationType) { toast.dispose() }.start()
        } else {
            JOptionPane.showMessageDialog(null, message)
        }
    }
}

private var composeWindowProvider: () -> ComposeWindow? = {
    null
}

fun setComposeWindowProvider(provider: () -> ComposeWindow?) {
    composeWindowProvider = provider
}