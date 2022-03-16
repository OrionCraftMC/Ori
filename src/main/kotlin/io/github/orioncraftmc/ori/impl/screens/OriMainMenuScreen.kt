package io.github.orioncraftmc.ori.impl.screens

import io.github.orioncraftmc.orion.api.bridge.MinecraftBridge
import io.github.orioncraftmc.orion.screens.menu.main.MainMenuScreen

class OriMainMenuScreen : MainMenuScreen() {
    override fun renderSkybox(mouseX: Int, mouseY: Int, renderPartialTicks: Float) {
        MinecraftBridge.drawDefaultBackground()
    }

    override fun superDrawScreen(mouseX: Int, mouseY: Int, renderPartialTicks: Float) {
        // NO-OP
    }

    override fun superHandleMouseClick(mouseX: Int, mouseY: Int, clickedButtonId: Int) {
        // NO-OP
    }
}