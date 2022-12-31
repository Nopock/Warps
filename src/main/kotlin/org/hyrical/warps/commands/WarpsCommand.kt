package org.hyrical.warps.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import org.bukkit.entity.Player
import org.hyrical.warps.config.WarpsFile
import org.hyrical.warps.utils.CC

object WarpsCommand : BaseCommand() {

    private val warps = WarpsFile.getConfig()

    @CommandAlias("warps")
    fun warps(player: Player) {
        val warpsList = warps.getKeys(false).joinToString(", ").capitalize()

        if (warpsList.isEmpty()){
            player.sendMessage(CC.translate("&cNo warps."))
            return
        }

        player.sendMessage(CC.translate("&aWarps: &e$warpsList"))
    }
}