package io.github.orioncraftmc.ori

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.clikt.parameters.types.file
import io.github.orioncraftmc.ori.impl.OriBootstrapper
import io.github.orioncraftmc.ori.impl.application.OriApplication
import io.github.orioncraftmc.orion.api.event.EventBus
import io.github.orioncraftmc.orion.api.event.impl.action.GameAction
import io.github.orioncraftmc.orion.api.event.impl.action.GameActionChangeEvent
import io.github.orioncraftmc.orion.api.meta.ClientVersion
import javafx.application.Application
import java.io.File

class OriCommand : CliktCommand(help = "Simulate the OrionCraft client.") {

    private val clientVersion: ClientVersion by option(
        "--client-version",
        help = "The client version to use. Defaults to Minecraft 1.5.2."
    ).enum<ClientVersion> { it.toString() }.default(ClientVersion.MC_1_5_2)

    private val isDevLaunch: Boolean by option(
        "--dev-launch",
        help = "Whether to launch in dev mode. Defaults to true."
    ).flag(default = true)

    private val clientDirectory: File by option(
        "--run-folder",
        help = "The folder to store the data in. Defaults to the run folder in the current directory."
    ).file(canBeDir = true).default(File(".", "run"))

    private val clientLocale: String by option(
        "--locale",
        help = "The locale to use. Defaults to en_US."
    ).default("en_US")

    override fun run() {
        OriBootstrapper.bootstrap(clientVersion, clientDirectory, clientLocale, isDevLaunch)

        /* Simulate Main Menu */
        EventBus.callEvent(GameActionChangeEvent(GameAction.MAIN_MENU))

        // Launch HelloApplication using javafx
        Application.launch(OriApplication::class.java)

    }
}

fun main(args: Array<String>) {
    OriCommand().main(args)
}

