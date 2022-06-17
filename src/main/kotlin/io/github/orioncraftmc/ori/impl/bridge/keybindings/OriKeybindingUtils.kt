package io.github.orioncraftmc.ori.impl.bridge.keybindings

import io.github.orioncraftmc.orion.api.bridge.MinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.input.KeybindingUtils
import io.github.orioncraftmc.orion.api.bridge.input.OrionKeybindingBridge
import io.github.orioncraftmc.orion.api.keybinding.OrionKeybinding
import io.github.orioncraftmc.orion.api.logger

object OriKeybindingUtils : KeybindingUtils {
    private val registeredKeybindings = mutableMapOf<String, OriKeybinding>()

    override fun registerKeybinding(keybind: OrionKeybinding): OrionKeybindingBridge {
        logger.debug("Registering Keybinding [${MinecraftBridge.translateString(keybind.id)}] with [${keybind.keyCode}] in the category [${keybind.category.friendlyName}]")

        return OriKeybinding(keybind).also { registeredKeybindings[keybind.id] = it }
    }

    override fun unregisterKeybinding(keybind: OrionKeybindingBridge) {
        logger.debug("Unregistering keybinding [${MinecraftBridge.translateString(keybind.orionKeybinding.id)}")

        registeredKeybindings.remove(keybind.orionKeybinding.id)
    }
}