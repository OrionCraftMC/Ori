package io.github.orioncraftmc.ori.impl

import io.github.orioncraftmc.ori.impl.bridge.OriBridgeProvider
import io.github.orioncraftmc.ori.impl.bridge.minecraft.OriMinecraftBridge
import io.github.orioncraftmc.orion.api.OrionCraft
import io.github.orioncraftmc.orion.api.meta.ClientVersion
import java.io.File

/**
 * Helper class for bootstrapping the Ori client.
 */
object OriBootstrapper {
    /**
     * Bootstraps the Ori client.
     *
     * @param clientVersion The version of the client to bootstrap.
     * @param clientDirectory The directory to bootstrap the client to.
     * @param isDevLaunch True if the client is being launched in development mode.
     */
    fun bootstrap(clientVersion: ClientVersion, clientDirectory: File, clientLocale: String, isDevLaunch: Boolean) {
        /* Set whether the client is in development mode. */
        System.setProperty("lightcraft.launch.dev", isDevLaunch.toString())

        /* Initialize OrionCraft by calling its entry point. */
        OrionCraft.startGameEntrypoint(clientVersion)

        /* Initialize required fields in Minecraft Bridge implementation */
        OriMinecraftBridge.initializeLocale(clientLocale)
        OriMinecraftBridge.gameAppDirectory = clientDirectory.also { it.mkdirs() }

        /* Then, after OrionCraft has been initialized, initialize Ori by passing the bridge implementations. */
        OrionCraft.setOrionCraftBridgesEntrypoint(OriBridgeProvider)
    }
}