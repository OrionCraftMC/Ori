package io.github.orioncraftmc.ori.impl.bridge.minecraft

import io.github.orioncraftmc.ori.impl.application.OriRenderLoop
import io.github.orioncraftmc.ori.impl.application.OriRenderType
import io.github.orioncraftmc.ori.impl.bridge.rendering.OriFontRendererBridge
import io.github.orioncraftmc.ori.impl.bridge.rendering.OriRenderEngineBridge
import io.github.orioncraftmc.ori.impl.bridge.settings.OriGameSettingsBridge
import io.github.orioncraftmc.ori.impl.screens.OriMainMenuScreen
import io.github.orioncraftmc.orion.api.bridge.MainMenuUtils
import io.github.orioncraftmc.orion.api.bridge.MinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.gui.GuiScreenBridge
import io.github.orioncraftmc.orion.api.bridge.main.MainMenuAction
import io.github.orioncraftmc.orion.api.bridge.minecraft.GameSettingsBridge
import io.github.orioncraftmc.orion.api.bridge.minecraft.MinecraftBridge
import io.github.orioncraftmc.orion.api.bridge.minecraft.ScaledResolutionBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.FontRendererBridge
import io.github.orioncraftmc.orion.api.bridge.rendering.RenderEngineBridge
import io.github.orioncraftmc.orion.api.event.EventBus
import io.github.orioncraftmc.orion.api.event.impl.LocaleLoadEvent
import io.github.orioncraftmc.orion.api.gui.hud.editor.ModsEditorScreen
import io.github.orioncraftmc.orion.api.gui.screens.OrionScreen
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.scene.transform.Scale
import javafx.stage.Stage
import java.io.File
import java.util.*

object OriMinecraftBridge : MinecraftBridge {

    private lateinit var locale: Properties
    lateinit var stage: Stage
    lateinit var renderType: OriRenderType
    lateinit var canvas: Canvas
    lateinit var scene: Scene
    lateinit var renderLoop: OriRenderLoop
    lateinit var guiScale: GuiScale

    fun initializeLocale(lang: String) {
        locale = Properties()
        EventBus.callEvent(LocaleLoadEvent(lang, locale))
    }

    fun initializeJavaFX(stage: Stage, scene: Scene, canvas: Canvas) {
        this.stage = stage
        this.scene = scene
        this.canvas = canvas
        this.renderLoop = OriRenderLoop(canvas, renderType).also { it.start() }

        arrayOf(canvas.widthProperty(), canvas.heightProperty(), stage.maximizedProperty()).forEach {
            it.addListener { _, _, _ ->
                OriScaledResolutionBridge.onGuiScaleUpdated()
            }
        }

        OriScaledResolutionBridge.onGuiScaleUpdated()

        canvas.setupMouseEvents()

        MinecraftBridge.openScreen(ModsEditorScreen(true))
    }

    private fun Canvas.setupMouseEvents() {
        setOnMouseReleased {
            val orionScreen = MinecraftBridge.currentOpenedScreen as? OrionScreen ?: return@setOnMouseReleased
            val mouseX = it.x.toInt()
            val mouseY = it.y.toInt()

            renderLoop.mouseX = it.x
            renderLoop.mouseY = it.y

            orionScreen.handleMouseRelease(mouseX, mouseY)
        }

        setOnMousePressed {
            val orionScreen = MinecraftBridge.currentOpenedScreen as? OrionScreen ?: return@setOnMousePressed
            val mouseX = it.x.toInt()
            val mouseY = it.y.toInt()

            updateMousePosFromEvent(it)
            orionScreen.handleMouseClick(mouseX, mouseY, it.button.ordinal)
        }

        setOnMouseDragged {
            updateMousePosFromEvent(it)
        }

        setOnMouseMoved {
            updateMousePosFromEvent(it)
        }
    }

    private fun updateMousePosFromEvent(it: MouseEvent) {
        renderLoop.mouseX = it.x
        renderLoop.mouseY = it.y
    }

    fun stop() {
        renderLoop.stop()
    }

    override var currentOpenedScreen: GuiScreenBridge? = null

    override var gameAppDirectory: File = File(".")

    override val currentFps: Int
        get() = renderLoop.fps

    override val fontRenderer: FontRendererBridge
        get() = OriFontRendererBridge

    override val gameWidth: Int
        get() = renderLoop.canvas.width.toInt()

    override val gameHeight: Int
        get() = renderLoop.canvas.height.toInt()

    override val gameSettings: GameSettingsBridge
        get() = OriGameSettingsBridge

    override val renderEngine: RenderEngineBridge
        get() = OriRenderEngineBridge

    override val scaledResolution: ScaledResolutionBridge
        get() = OriScaledResolutionBridge

    override fun drawDefaultBackground() {
        renderLoop.currentRenderContext.fill = Color.rgb(0, 0, 0, 0.435)
        renderLoop.currentRenderContext.fillRect(0.0, 0.0, renderLoop.canvas.width, renderLoop.canvas.height)
    }

    override fun openScreen(screen: OrionScreen?) {
        (currentOpenedScreen as? OrionScreen)?.onClose()
        currentOpenedScreen = (screen ?: OriMainMenuScreen()).also { it.onResize() }
    }

    fun notifyResize(w: Double, h: Double) {
        (currentOpenedScreen as? OrionScreen)?.onResize()
    }

    override fun translateString(translationKey: String): String {
        return locale.getProperty(translationKey) ?: translationKey
    }

    fun onScaleFactorChanged(scaleFactor: Int) {
        val factor = scaleFactor.toDouble()
        OriFontRendererBridge.resetMinecraftFont()
        scene.root.transforms.setAll(Scale(factor, factor).also {
            it.pivotX = 0.0; it.pivotY = 0.0
        })
    }

}