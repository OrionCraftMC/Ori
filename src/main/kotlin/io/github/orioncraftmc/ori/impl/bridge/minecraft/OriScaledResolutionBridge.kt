package io.github.orioncraftmc.ori.impl.bridge.minecraft

import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge

object OriScaledResolutionBridge : ScaledResolutionBridge {

    override val scaleFactor: Int
        get() = 1

    override val scaledHeight: Int
        get() = scaledHeightFloat.toInt()

    override val scaledHeightFloat: Float
        get() = OriMinecraftBridge.canvas.height.toFloat()

    override val scaledWidth: Int
        get() = scaledWidthFloat.toInt()

    override val scaledWidthFloat: Float
        get() = OriMinecraftBridge.canvas.width.toFloat()
}