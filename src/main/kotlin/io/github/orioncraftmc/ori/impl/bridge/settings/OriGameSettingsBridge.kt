package io.github.orioncraftmc.ori.impl.bridge.settings

import io.github.orioncraftmc.ori.impl.bridge.minecraft.GuiScale
import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriMinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.minecraft.GameSettingsBridge

object OriGameSettingsBridge : GameSettingsBridge {
    override var gammaValue: Float by GameSettingsLogDelegate(1f)

    override var guiScale: Int
        get() {
            return OriMinecraftBridge.guiScale.ordinal
        }
        set(value) {
            OriMinecraftBridge.guiScale = GuiScale.values()[value]
        }

    override var isDebugInfoVisible: Boolean by GameSettingsLogDelegate(false)
}