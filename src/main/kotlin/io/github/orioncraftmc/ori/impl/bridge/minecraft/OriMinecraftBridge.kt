package io.github.orioncraftmc.ori.impl.bridge.minecraft

import io.github.orioncraftmc.ori.impl.application.OriRenderLoop
import io.github.orioncraftmc.ori.impl.bridge.rendering.OriFontRendererBridge
import io.github.orioncraftmc.ori.impl.bridge.rendering.OriRenderEngineBridge
import io.github.orioncraftmc.ori.impl.bridge.settings.OriGameSettingsBridge
import io.github.orioncraftmc.ori.impl.screens.OriMainMenuScreen
import io.github.orioncraftmc.orion.api.bridge.MinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.gui.GuiScreenBridge
import io.github.orioncraftmc.orion.api.bridge.minecraft.GameSettingsBridge
import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.FontRendererBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.RenderEngineBridge
import io.github.orioncraftmc.orion.api.event.EventBus
import io.github.orioncraftmc.orion.api.event.impl.LocaleLoadEvent
import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.stage.Stage
import java.io.File
import java.util.*

object OriMinecraftBridge : MinecraftBridge {

    private lateinit var locale: Properties
    lateinit var stage: Stage
    lateinit var canvas: Canvas
    lateinit var scene: Scene
    lateinit var renderLoop: OriRenderLoop

    fun initializeLocale(lang: String) {
        locale = Properties()
        EventBus.callEvent(LocaleLoadEvent(lang, locale))
    }

    fun initializeJavaFX(stage: Stage, scene: Scene, canvas: Canvas) {
        this.stage = stage
        this.scene = scene
        this.canvas = canvas
        this.renderLoop = OriRenderLoop(canvas).also { it.start() }

        canvas.setOnMouseMoved {
            renderLoop.mouseX = it.x
            renderLoop.mouseY = it.y
        }

        MinecraftBridge.openScreen(OriMainMenuScreen())
    }

    fun stop() {
        renderLoop.stop()
    }

    override var currentOpenedScreen: GuiScreenBridge? = null

    override var gameAppDirectory: File = File(".")

    override val currentFps: Int
        get() = TODO("Not yet implemented")

    override val fontRenderer: FontRendererBridge
        get() = OriFontRendererBridge

    override val gameHeight: Int
        get() = TODO("Not yet implemented")

    override val gameSettings: GameSettingsBridge
        get() = OriGameSettingsBridge

    override val gameWidth: Int
        get() = TODO("Not yet implemented")

    override val renderEngine: RenderEngineBridge
        get() = OriRenderEngineBridge

    override val scaledResolution: ScaledResolutionBridge
        get() = OriScaledResolutionBridge

    override fun drawDefaultBackground() {
        TODO("Not yet implemented")
    }

    override fun openScreen(screen: OrionScreen?) {
        (currentOpenedScreen as? OrionScreen)?.onClose()
        currentOpenedScreen = screen?.also { it.onResize() }
    }

    fun notifyResize(w: Double, h: Double) {
        (currentOpenedScreen as? OrionScreen)?.onResize()
    }

    override fun translateString(translationKey: String): String {
        return locale.getProperty(translationKey) ?: translationKey
    }

}