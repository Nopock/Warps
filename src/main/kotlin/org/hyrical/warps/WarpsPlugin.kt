package org.hyrical.warps

import co.aikar.commands.PaperCommandManager
import org.bukkit.plugin.java.JavaPlugin
import org.hyrical.warps.commands.WarpAdminCommands
import org.hyrical.warps.commands.WarpCommands
import org.hyrical.warps.commands.WarpsCommand

class WarpsPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: WarpsPlugin
    }

    override fun onEnable() {
        instance = this
        saveDefaultConfig()

        val manager = PaperCommandManager(this)
        manager.registerCommand(WarpsCommand)
        manager.registerCommand(WarpAdminCommands)
        manager.registerCommand(WarpCommands)

    }
}