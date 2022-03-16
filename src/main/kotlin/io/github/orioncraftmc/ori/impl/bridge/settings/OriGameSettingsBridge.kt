package io.github.orioncraftmc.ori.impl.bridge.settings

import io.github.orioncraftmc.orion.api.bridge.minecraft.GameSettingsBridge

object OriGameSettingsBridge : GameSettingsBridge {
    override var gammaValue: Float by GameSettingsLogDelegate(1f)

    override val guiScale: Int by GameSettingsLogDelegate(1)
}