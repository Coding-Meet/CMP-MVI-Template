package com.example.cmp_mvi_template.core.platform.toast

import java.awt.*
import java.awt.geom.RoundRectangle2D
import javax.swing.*

class RoundedPanel(
    private val arcWidth: Int = 24,
    private val arcHeight: Int = 24,
    private val bgColor: Color = Color(50, 50, 50, 220)
) : JPanel() {
    init {
        isOpaque = false // Allow transparency
        layout = FlowLayout()
    }

    override fun paintComponent(g: Graphics) {
        val g2 = g as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g2.color = bgColor
        g2.fill(
            RoundRectangle2D.Float(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                arcWidth.toFloat(),
                arcHeight.toFloat()
            )
        )
        super.paintComponent(g)
    }
}