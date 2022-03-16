package io.github.orioncraftmc.ori.impl.bridge.resource

import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationBridge

data class OriResourceLocation(override val namespace: String, override val resource: String) : ResourceLocationBridge {
    override val fullPath: String
        get() = "/assets/$namespace/$resource"
}