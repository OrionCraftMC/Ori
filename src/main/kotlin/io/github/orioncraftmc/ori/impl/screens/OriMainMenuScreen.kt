package io.github.orioncraftmc.ori.impl.screens

import io.github.orioncraftmc.components.utils.ComponentUtils
import io.github.orioncraftmc.orion.screens.menu.main.MainMenuScreen
import io.github.orioncraftmc.orion.utils.ColorConstants

class OriMainMenuScreen : MainMenuScreen() {
    override fun renderSkybox(mouseX: Int, mouseY: Int, renderPartialTicks: Float) {
        ComponentUtils.renderBackgroundColor(this, ColorConstants.modLabelBackgroundColor)
    }

    override fun superDrawScreen(mouseX: Int, mouseY: Int, renderPartialTicks: Float) {
        // NO-OP
    }

    override fun superHandleMouseClick(mouseX: Int, mouseY: Int, clickedButtonId: Int) {
        // NO-OP
    }
}