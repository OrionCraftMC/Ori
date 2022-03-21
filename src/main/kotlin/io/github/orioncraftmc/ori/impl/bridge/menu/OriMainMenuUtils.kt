package io.github.orioncraftmc.ori.impl.bridge.menu

import io.github.orioncraftmc.moveable.screens.AbleExampleScreen
import io.github.orioncraftmc.orion.api.bridge.MinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.main.MainMenuAction
import io.github.orioncraftmc.orion.api.bridge.main.MainMenuUtils
import io.github.orioncraftmc.orion.api.logger
import kotlin.system.exitProcess

object OriMainMenuUtils : MainMenuUtils {
    override fun executeMainMenuAction(action: MainMenuAction) {
        when (action) {
            MainMenuAction.OPTIONS -> {
                MinecraftBridge.openScreen(AbleExampleScreen())
            }
            MainMenuAction.EXIT_GAME -> exitProcess(0)
            else -> logger.debug("Main menu action [$action]")
        }
    }

    override fun getTranslationForMainMenuAction(action: MainMenuAction): String {
        return when (action) {
            MainMenuAction.SINGLEPLAYER -> "Singleplayer"
            MainMenuAction.MULTIPLAYER -> "Multiplayer"
            MainMenuAction.LANGUAGE_PICKER -> "Language Picker"
            MainMenuAction.OPTIONS -> "Options"
            MainMenuAction.EXIT_GAME -> "Quit Game"
            else -> ""
        }
    }
}