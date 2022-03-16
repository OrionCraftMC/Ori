package io.github.orioncraftmc.ori.impl.bridge

import io.github.orioncraftmc.ori.impl.bridge.keybindings.OriKeybindingUtils
import io.github.orioncraftmc.ori.impl.bridge.menu.OriMainMenuUtils
import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriMinecraftBridge
import io.github.orioncraftmc.ori.impl.bridge.rendering.OriOpenGlBridge
import io.github.orioncraftmc.ori.impl.bridge.rendering.OriTessellatorBridge
import io.github.orioncraftmc.ori.impl.bridge.resource.OriResourceLocationUtils
import io.github.orioncraftmc.orion.api.bridge.OrionCraftBridgeProvider
import io.github.orioncraftmc.orion.api.bridge.input.KeybindingUtils
import io.github.orioncraftmc.orion.api.bridge.main.MainMenuUtils
import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.minecraft.resources.ResourceLocationUtils
import io.github.orioncraftmc.orion.api.bridge.rendering.OpenGlBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.TessellatorBridge
import io.github.orioncraftmc.orion.api.logging.FallbackLogger
import io.github.orioncraftmc.orion.api.logging.Logger

object OriBridgeProvider : OrionCraftBridgeProvider {

    override val keybindingUtils: KeybindingUtils
        get() = OriKeybindingUtils

    override val logger: Logger
        get() = FallbackLogger

    override val mainMenuUtils: MainMenuUtils
        get() = OriMainMenuUtils

    override val minecraftBridge: MinecraftBridge
        get() = OriMinecraftBridge

    override val openGlBridge: OpenGlBridge
        get() = OriOpenGlBridge

    override val resourceLocationUtils: ResourceLocationUtils
        get() = OriResourceLocationUtils

    override val tessellator: TessellatorBridge
        get() = OriTessellatorBridge
}