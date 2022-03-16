package io.github.orioncraftmc.ori.impl.bridge.rendering

import com.github.ajalt.colormath.model.RGBInt
import com.sun.javafx.tk.Toolkit
import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriMinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.FontRendererBridge
import javafx.scene.paint.Color
import javafx.scene.text.Font

object OriFontRendererBridge : FontRendererBridge {

    private val fontSize = 16

    private val font = Font.loadFont(OriFontRendererBridge::class.java.getResourceAsStream("/Minecraftia.ttf"), fontSize.toDouble())
    private val metrics = Toolkit.getToolkit().fontLoader.getFontMetrics(font)

    override val fontHeight: Int
        get() = metrics.lineHeight.toInt()

    override fun drawString(value: String, x: Int, y: Int, color: UInt, hasShadow: Boolean) {

        if (hasShadow) {
            val original = RGBInt(color).toSRGB().toHSL()
            drawString(value, x + 2, y + 2, original.copy(l = original.l - 25).toSRGB().toRGBInt().argb, false)
        }

        val ctx = OriMinecraftBridge.renderLoop.renderContext
        ctx.font = font
        val height = fontHeight.toDouble()

        val (r, g, b, a) = RGBInt(color)

        ctx.fill = Color.rgb(r.toInt(), g.toInt(), b.toInt(), a.toInt() / 255.0)
        ctx.fillText(value, x.toDouble(), (y.toDouble() + height - metrics.descent))
    }

    override fun getStringWidth(value: String): Int = value.sumOf { metrics.getCharWidth(it).toInt() }
}