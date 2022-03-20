package io.github.orioncraftmc.ori.impl.bridge.settings

import io.github.orioncraftmc.orion.api.logger
import kotlin.reflect.KProperty

data class GameSettingsLogDelegate<T>(var value: T, var onChanged: ((T) -> Unit)? = null) {

    operator fun getValue(bridge: OriGameSettingsBridge, property: KProperty<*>): T {
        return value
    }

    operator fun setValue(bridge: OriGameSettingsBridge, property: KProperty<*>, value: T) {
        this.value = value
        logger.debug("Setting GameSetting [${property.name}] to $value")
        onChanged?.invoke(value)
    }
}