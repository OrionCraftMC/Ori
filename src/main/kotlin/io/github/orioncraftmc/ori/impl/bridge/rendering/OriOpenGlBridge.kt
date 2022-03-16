package io.github.orioncraftmc.ori.impl.bridge.rendering

import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriMinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.OpenGlBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.enums.GlCapability

object OriOpenGlBridge : OpenGlBridge {
    override fun disableCapability(capability: GlCapability) {
        
    }

    override fun enableBlendAlphaMinusSrcAlpha() {
        
    }

    override fun enableCapability(capability: GlCapability) {
        
    }

    override fun popMatrix() {
        OriMinecraftBridge.renderLoop.renderContext.restore()
    }

    override fun pushMatrix() {
        OriMinecraftBridge.renderLoop.renderContext.save()
    }

    override fun scale(x: Double, y: Double, z: Double) {
        OriMinecraftBridge.renderLoop.renderContext.scale(x, y)
    }

    override fun setColor(red: Int, green: Int, blue: Int, alpha: Int) {
        
    }

    override fun setLineWidth(width: Float) {
        
    }

    override fun translate(x: Double, y: Double, z: Double) {
        OriMinecraftBridge.renderLoop.renderContext.translate(x, y)
    }
}