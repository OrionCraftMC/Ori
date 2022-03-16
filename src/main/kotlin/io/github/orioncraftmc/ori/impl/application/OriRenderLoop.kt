package io.github.orioncraftmc.ori.impl.application

import io.github.orioncraftmc.orion.api.bridge.MinecraftBridge
import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen
import javafx.animation.AnimationTimer
import javafx.scene.canvas.Canvas

class OriRenderLoop(val canvas: Canvas) : AnimationTimer() {
    private var lastFrameTime = 0L
    private var deltaFrameTime = 0L
    private var fps = 0
    var mouseX: Double = 0.0
    var mouseY: Double = 0.0

    val renderContext = canvas.graphicsContext2D


    override fun handle(now: Long) {
        computeFrameTimes(now)

        renderContext.clearRect(0.0, 0.0, canvas.width, canvas.height)

        // Draw fps on screen
        renderContext.fillText("FPS: $fps", 10.0, 10.0)


        (MinecraftBridge.currentOpenedScreen as? OrionScreen)?.drawScreen(mouseX.toInt(), mouseY.toInt(), 0f)
    }

    private fun computeFrameTimes(now: Long) {
        // Calculate the delta time (in nanoseconds)
        deltaFrameTime = now - lastFrameTime
        lastFrameTime = now
        fps = (1000000000 / deltaFrameTime).toInt()
    }
}