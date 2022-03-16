package io.github.orioncraftmc.ori.impl.application

import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriMinecraftBridge
import javafx.scene.canvas.Canvas
import javafx.scene.layout.Pane

class CanvasPane(width: Double, height: Double) : Pane() {
    val canvas: Canvas

    init {
        canvas = Canvas(width, height)
        children.add(canvas)
    }

    override fun layoutChildren() {
        super.layoutChildren()
        val x = snappedLeftInset()
        val y = snappedTopInset()
        val w = snapSizeX(width) - x - snappedRightInset()
        val h = snapSizeY(height) - y - snappedBottomInset()
        canvas.layoutX = x
        canvas.layoutY = y
        canvas.width = w
        canvas.height = h

        OriMinecraftBridge.notifyResize(w, h)
    }
}
