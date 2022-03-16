package io.github.orioncraftmc.ori.impl.bridge.rendering

import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.RenderEngineBridge
import javafx.scene.image.Image

object OriRenderEngineBridge : RenderEngineBridge {
    var image: Image? = null
    var lastImage: String? = null
    override fun bindTexture(texture: ResourceLocationBridge) {
        val path = texture.fullPath
        if (path == lastImage) return

        OriRenderEngineBridge::class.java.getResourceAsStream(path).use {
            image = Image(it)
            lastImage = path
        }
    }
}