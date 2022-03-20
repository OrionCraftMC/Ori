package io.github.orioncraftmc.ori.impl.bridge.minecraft

import io.github.orioncraftmc.ori.impl.bridge.settings.OriGameSettingsBridge
import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge
import kotlin.math.ceil

object OriScaledResolutionBridge : ScaledResolutionBridge {
    fun onGuiScaleUpdated() {
        scaledWidth = OriMinecraftBridge.gameWidth
        scaledHeight = OriMinecraftBridge.gameHeight
        scaleFactor = 1
        var currentGuiScale: Int = OriGameSettingsBridge.guiScale
        if (currentGuiScale == 0) {
            currentGuiScale = 1000
        }

        var tempScaleFactor = scaleFactor
        while (tempScaleFactor < currentGuiScale && scaledWidth / (tempScaleFactor + 1) >= 320 && scaledHeight / (tempScaleFactor + 1) >= 240) {
            ++tempScaleFactor
        }
        scaleFactor = tempScaleFactor

        scaledWidthFloat = scaledWidth.toFloat() / scaleFactor.toFloat()
        scaledHeightFloat = scaledHeight.toFloat() / scaleFactor.toFloat()
        scaledWidth = ceil(scaledWidthFloat).toInt()
        scaledHeight = ceil(scaledHeightFloat).toInt()
    }

    override var scaleFactor: Int = 1
    set(value) {
        field = value
        OriMinecraftBridge.onScaleFactorChanged(value)
    }

    override var scaledWidth: Int = 0
    override var scaledHeight: Int = 0
    override var scaledWidthFloat: Float = 0f
    override var scaledHeightFloat: Float = 0f
}