package io.github.orioncraftmc.ori.impl.bridge.rendering

import com.github.ajalt.colormath.model.RGBInt
import com.sun.javafx.tk.Toolkit
import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriMinecraftBridge
import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriScaledResolutionBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.FontRendererBridge
import javafx.scene.paint.Color
import javafx.scene.text.Font

object OriFontRendererBridge : FontRendererBridge {

    private const val fontSize = 16

    private lateinit var font: Font

    init{
        resetMinecraftFont()
    }

    fun resetMinecraftFont() {
        font = Font.loadFont(OriFontRendererBridge::class.java.getResourceAsStream("/Minecraftia.ttf"), fontSize.toDouble() / OriScaledResolutionBridge.scaleFactor)
    }

    private val metrics = Toolkit.getToolkit().fontLoader.getFontMetrics(font)

    override val fontHeight: Int
        get() = metrics.lineHeight.toInt() / OriScaledResolutionBridge.scaleFactor

    override fun drawString(value: String, x: Int, y: Int, color: UInt, hasShadow: Boolean) {
        if (hasShadow) {
            val original = RGBInt(color).toSRGB().toHSL()
            drawString(value, x + 1, y + 1, original.copy(l = original.l - 25).toSRGB().toRGBInt().argb, false)
        }

        val ctx = OriMinecraftBridge.renderLoop.currentRenderContext
        ctx.font = font
        val height = fontHeight.toDouble()

        val (r, g, b, a) = RGBInt(color)

        ctx.fill = Color.rgb(r.toInt(), g.toInt(), b.toInt(), a.toInt() / 255.0)
        val descent = metrics.descent / OriScaledResolutionBridge.scaleFactor
        ctx.fillText(value, x.toDouble(), (y.toDouble() + height - descent))
    }

    private fun stripColors(value: String): String {
        return value.replace("§[\\da-fA-Fl-oL-O]".toRegex(), "")
    }

    override fun getStringWidth(value: String): Int =
        stripColors(value).sumOf { metrics.getCharWidth(it).toInt() } / OriScaledResolutionBridge.scaleFactor
}