package io.github.orioncraftmc.ori.impl.bridge.rendering

import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriMinecraftBridge
import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriScaledResolutionBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.TessellatorBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.enums.DrawMode
import javafx.scene.canvas.GraphicsContext
import javafx.scene.image.Image
import javafx.scene.paint.Color

object OriTessellatorBridge : TessellatorBridge {
    private val xVertices = mutableListOf<Double>()
    private val yVertices = mutableListOf<Double>()
    private val xUVVertices = mutableListOf<Double>()
    private val yUVVertices = mutableListOf<Double>()
    private var drawMode = DrawMode.POINTS
    private var color = Color.BLACK
    private var isDrawImage = false

    override fun addVertex(x: Double, y: Double, z: Double) {
        xVertices.add(x)
        yVertices.add(y)
    }

    override fun addVertexWithUV(x: Double, y: Double, z: Double, u: Double, v: Double) {
        isDrawImage = true
        addVertex(x, y, z)
        xUVVertices.add(u * OriRenderEngineBridge.image!!.width)
        yUVVertices.add(v * OriRenderEngineBridge.image!!.height)
    }

    override fun draw() {
        OriMinecraftBridge.renderLoop.currentRenderContext.run {
            if (drawMode == DrawMode.QUADS) {
                fill = color
                val image = OriRenderEngineBridge.image
                if (isDrawImage && image != null) {
                    renderImageQuad(image)
                } else {
                    fillPolygon(xVertices.toDoubleArray(), yVertices.toDoubleArray(), xVertices.size)
                }
            } else if (drawMode == DrawMode.LINE_LOOP) {
                stroke = color
                lineWidth = (OriOpenGlBridge.lineWidth / OriScaledResolutionBridge.scaleFactor).toDouble()
                if (xVertices.isNotEmpty()) xVertices.add(xVertices[0]) // Add the first vertex to the end to close the loop
                if (yVertices.isNotEmpty()) yVertices.add(yVertices[0]) // Add the first vertex to the end to close the loop
                strokePolygon(xVertices.toDoubleArray(), yVertices.toDoubleArray(), xVertices.size)
            }
        }

        xVertices.clear()
        yVertices.clear()
        xUVVertices.clear()
        yUVVertices.clear()
        isDrawImage = false
    }

    private fun GraphicsContext.renderImageQuad(image: Image) {
        // Get top left and bottom right points of the quad
        val topLeftX = xVertices.minOrNull() ?: 0.0
        val topLeftY = yVertices.minOrNull() ?: 0.0

        val bottomRightX = xVertices.maxOrNull() ?: 0.0
        val bottomRightY = yVertices.maxOrNull() ?: 0.0

        // Find the width and height of the quad
        val width = bottomRightX - topLeftX
        val height = bottomRightY - topLeftY

        // Find index of top left point
        val topLeftXIndex = xVertices.indexOf(topLeftX)
        val topLeftYIndex = yVertices.indexOf(topLeftY)

        // Find corresponding UV coordinates
        val topLeftUVX = xUVVertices[topLeftXIndex]
        val topLeftUVY = yUVVertices[topLeftYIndex]

        // Find index of bottom right point
        val bottomRightXIndex = xVertices.indexOf(bottomRightX)
        val bottomRightYIndex = yVertices.indexOf(bottomRightY)

        // Find corresponding UV coordinates
        val bottomRightUVX = xUVVertices[bottomRightXIndex]
        val bottomRightUVY = yUVVertices[bottomRightYIndex]

        // Find the width and height of the uv quad
        val uvWidth = bottomRightUVX - topLeftUVX
        val uvHeight = bottomRightUVY - topLeftUVY

        drawImage(
            image,
            topLeftUVX, topLeftUVY, uvWidth, uvHeight,
            topLeftX, topLeftY, width, height
        )
    }

    override fun setColor(red: Int, green: Int, blue: Int, alpha: Int) {
        setTesselatorColor(red, green, blue, alpha)
    }

    override fun setTesselatorColor(red: Int, green: Int, blue: Int, alpha: Int) {
        color = Color.rgb(red, green, blue, alpha / 255.0)
    }

    override fun start(mode: DrawMode) {
        drawMode = mode
    }
}