package io.github.orioncraftmc.ori.impl.application

import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriGuiIngameBridge
import io.github.orioncraftmc.orion.api.bridge.MinecraftBridge
import io.github.orioncraftmc.orion.api.event.InstaEventBus
import io.github.orioncraftmc.orion.api.event.impl.HudRenderEvent
import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen
import javafx.animation.AnimationTimer
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext

class OriRenderLoop(val canvas: Canvas, val type: OriRenderType) : AnimationTimer() {
    private var lastFrameTime = 0L
    private var deltaFrameTime = 0L
    var fps = 0
    var mouseX: Double = 0.0
    var mouseY: Double = 0.0

    val currentRenderContext: GraphicsContext = canvas.graphicsContext2D

    override fun handle(now: Long) {
        computeFrameTimes(now)

        currentRenderContext.isImageSmoothing = false

        currentRenderContext.save()

        currentRenderContext.clearRect(0.0, 0.0, canvas.width, canvas.height)

        when (type) {
            OriRenderType.CURRENT_SCREEN -> {
                (MinecraftBridge.currentOpenedScreen as? OrionScreen)?.drawScreen(mouseX.toInt(), mouseY.toInt(), 0f)
            }
            OriRenderType.INGAME_HUD -> {
                InstaEventBus.post(HudRenderEvent(0f, OriGuiIngameBridge))
            }
        }


        currentRenderContext.restore()
    }

    private fun computeFrameTimes(now: Long) {
        // Calculate the delta time (in nanoseconds)
        deltaFrameTime = now - lastFrameTime
        lastFrameTime = now
        fps = (1000000000 / deltaFrameTime).toInt()
    }
}