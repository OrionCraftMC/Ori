package io.github.orioncraftmc.ori.impl.bridge.resource

import io.github.orioncraftmc.orion.api.OrionCraftConstants
import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationBridge
import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationUtils

object OriResourceLocationUtils : ResourceLocationUtils {
    override fun createNewMinecraftResourceLocation(resource: String): ResourceLocationBridge {
        return OriResourceLocation(OrionCraftConstants.MINECRAFT_RESOURCE_LOCATION_NS, resource)
    }

    override fun createNewOrionResourceLocation(resource: String): ResourceLocationBridge {
        return OriResourceLocation(OrionCraftConstants.ORION_RESOURCE_LOCATION_NS, resource)
    }
}