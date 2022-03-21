package io.github.orioncraftmc.moveable

import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen

class AbleManager(val screen: OrionScreen, vararg val ables: Able) {

    fun handleScreenRender(mouseX: Int, mouseY: Int) {

    }

    fun handleMouseDown(mouseX: Int, mouseY: Int, clickedButtonId: Int): Boolean {
        return false
    }

    fun handleMouseUp(mouseX: Int, mouseY: Int): Boolean {
        return false
    }

}