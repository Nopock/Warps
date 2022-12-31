package org.hyrical.warps.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CatchUnknown
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandCompletion
import co.aikar.commands.annotation.Name
import co.aikar.commands.annotation.Single
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.hyrical.warps.WarpsPlugin
import org.hyrical.warps.config.WarpsFile
import org.hyrical.warps.utils.CC

object WarpCommands : BaseCommand() {

    private val warps = WarpsFile.getConfig()

    @CommandAlias("warp")
    @CommandCompletion("@warps")
    @CatchUnknown
    fun warp(player: Player, @Name("name") @Single name1: String) {
        val name = name1.lowercase()

        if (!warps.contains(name)) {
            player.sendMessage(CC.translate("&cThat warp doesn't exist."))
            return
        }

        val location = Location(
            Bukkit.getWorld(warps.getString("$name.WORLD")!!),
            warps.getDouble("$name.X"),
            warps.getDouble("$name.Y"),
            warps.getDouble("$name.Z"),
            warps.getDouble("$name.YAW").toFloat(),
            warps.getDouble("$name.PITCH").toFloat()
        )

        object : BukkitRunnable() {
            var remaining = 3

            val lastLocation = player.location

            override fun run() {
                if (remaining == 0) {
                    player.teleport(location)
                    player.sendMessage(CC.translate("&aYou have been teleported to &e$name1&a."))
                    cancel()
                    return
                }

                if (player.location.x != lastLocation.x || player.location.z != lastLocation.z) {
                    player.sendMessage(CC.translate("&cYou moved! Teleportation cancelled."))
                    cancel()
                    return
                }

                player.sendMessage(CC.translate("&aTeleporting in &e$remaining&a..."))
                remaining--
            }
        }.runTaskTimer(WarpsPlugin.instance, 0L, 20L)
    }
}