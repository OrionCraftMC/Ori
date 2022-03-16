package io.github.orioncraftmc.ori.impl.application

import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriMinecraftBridge
import io.github.orioncraftmc.orion.api.OrionCraftConstants
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.system.exitProcess

class OriApplication : Application() {
    override fun start(stage: Stage) {
        val canvasPane = CanvasPane(856.0, 482.0)
        val scene = Scene(canvasPane, 856.0, 482.0)

        val canvas = canvasPane.canvas

        stage.title = OrionCraftConstants.clientTitle
        stage.scene = scene
        stage.show()

        OriMinecraftBridge.initializeJavaFX(stage, scene, canvas)

        stage.setOnCloseRequest {
            OriMinecraftBridge.stop()
            stage.close()
            exitProcess(1)
        }

    }
}