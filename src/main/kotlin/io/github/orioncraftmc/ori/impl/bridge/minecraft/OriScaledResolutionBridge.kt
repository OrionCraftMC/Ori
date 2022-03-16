package io.github.orioncraftmc.ori.impl.bridge.minecraft

import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge

object OriScaledResolutionBridge : ScaledResolutionBridge {
    override val scaleFactor: Int
        get() = 1

    override val scaledHeight: Int
        get() = OriMinecraftBridge.canvas.height.toInt()

    override val scaledHeightFloat: Float
        get() = scaledHeight.toFloat()

    override val scaledWidth: Int
        get() = OriMinecraftBridge.canvas.width.toInt()

    override val scaledWidthFloat: Float
        get() = scaledWidth.toFloat()
}