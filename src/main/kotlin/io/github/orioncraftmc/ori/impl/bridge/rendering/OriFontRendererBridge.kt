package io.github.orioncraftmc.ori.impl.bridge.rendering

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
        val ctx = OriMinecraftBridge.renderLoop.renderContext
        ctx.font = font
        val height = fontHeight.toDouble()

        // argb to red green blue alpha
        val r = (color shr 16 and 255u).toDouble() / 255.0
        val g = (color shr 8 and 255u).toDouble() / 255.0
        val b = (color and 255u).toDouble() / 255.0
        val a = (color shr 24 and 255u).toDouble() / 255.0
        ctx.fill = Color.rgb(r.toInt(), g.toInt(), b.toInt(), a)

        ctx.fillText(value, x.toDouble(), y.toDouble() + height)
    }

    override fun getStringWidth(value: String): Int = value.sumOf { metrics.getCharWidth(it).toInt() }
}