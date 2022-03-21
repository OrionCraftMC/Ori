package io.github.orioncraftmc.moveable.screens

import io.github.orioncraftmc.components.containers.ComponentContainer
import io.github.orioncraftmc.components.model.Anchor
import io.github.orioncraftmc.components.model.Point
import io.github.orioncraftmc.components.model.Size
import io.github.orioncraftmc.moveable.AbleManager
import io.github.orioncraftmc.moveable.Moveable
import io.github.orioncraftmc.orion.api.bridge.MinecraftBridge
import io.github.orioncraftmc.orion.api.gui.components.impl.LabelComponent
import io.github.orioncraftmc.orion.api.gui.components.screens.ComponentOrionScreen
import io.github.orioncraftmc.orion.utils.ColorConstants

class AbleExampleScreen : ComponentOrionScreen() {
    val ableManager = AbleManager(this, Moveable())

    init {
        for (i in 1..3) addComponent(createLabel("Moveable #$i"))
    }

    var y = 0.0
    private fun createLabel(function: String) = ComponentContainer().apply {
        addComponent(LabelComponent(function).apply {
            anchor = Anchor.MIDDLE
        })
        position = Point(0.0, y).also { y += 30.0 }
        size = Size(65.0, 30.0)
        backgroundColor = ColorConstants.modLabelBackgroundColor
    }

    override fun handleMouseClick(mouseX: Int, mouseY: Int, clickedButtonId: Int) {
        if (!ableManager.handleMouseDown(mouseX, mouseY, clickedButtonId)) {
            super.handleMouseClick(mouseX, mouseY, clickedButtonId)
        }

    }

    override fun handleMouseRelease(mouseX: Int, mouseY: Int) {
        if (!ableManager.handleMouseUp(mouseX, mouseY)) {
            super.handleMouseRelease(mouseX, mouseY)
        }

    }

    override fun drawScreen(mouseX: Int, mouseY: Int, renderPartialTicks: Float) {
        MinecraftBridge.drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, renderPartialTicks)
        ableManager.handleScreenRender(mouseX, mouseY)
    }
}