package io.github.orioncraftmc.ori.impl.bridge.keybindings

import io.github.orioncraftmc.orion.api.bridge.MinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.input.OrionKeybindingBridge
import io.github.orioncraftmc.orion.api.keybinding.OrionKeybinding

data class OriKeybinding(override val orionKeybinding: OrionKeybinding) : OrionKeybindingBridge {

    override val defaultKeyCode: Int = orionKeybinding.keyCode.keyCode

    override val description: String
        get() = MinecraftBridge.translateString(orionKeybinding.id)

    override var isPressed: Boolean
        get() = false
        set(value) {}

    override var keyCode: Int = defaultKeyCode
}