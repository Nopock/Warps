package org.hyrical.warps.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Name
import co.aikar.commands.annotation.Single
import co.aikar.commands.annotation.Subcommand
import org.bukkit.entity.Player
import org.hyrical.warps.WarpsPlugin
import org.hyrical.warps.config.WarpsFile
import org.hyrical.warps.utils.CC

@CommandAlias("warpadmin")
@CommandPermission("core.warps")
object WarpAdminCommands : BaseCommand() {

    private val warps = WarpsFile.getConfig()

    @Subcommand("create")
    fun create(player: Player, @Name("name") @Single name1: String) {
        val location = player.location.toCenterLocation()
        val name = name1.lowercase()

        if (warps.contains(name)) {
            player.sendMessage(CC.translate("&cA warp with that name already exists."))
            return
        }

        warps.set("$name.X", location.blockX)
        warps.set("$name.Y", location.blockY)
        warps.set("$name.Z", location.blockZ)
        warps.set("$name.YAW", location.yaw)
        warps.set("$name.PITCH", location.pitch)
        warps.set("$name.WORLD", location.world.name)


        player.sendMessage(CC.translate("&aWarp created."))
    }

    @Subcommand("delete")
    fun delete(player: Player, @Name("name") @Single name1: String) {
        val name = name1.lowercase()

        if (!warps.contains(name)) {
            player.sendMessage(CC.translate("&cA warp with that name doesn't exist."))
            return
        }

        warps.set(name, null)
        warps.save()

        player.sendMessage(CC.translate("&aWarp deleted."))
    }

    @Subcommand("set")
    fun set(player: Player, @Name("name") @Single name1: String) {
        val name = name1.lowercase()

        if (!warps.contains(name)) {
            player.sendMessage(CC.translate("&cA warp with that name doesn't exist."))
            return
        }

        val location = player.location.toCenterLocation()

        warps.set("$name.X", location.blockX)
        warps.set("$name.Y", location.blockY)
        warps.set("$name.Z", location.blockZ)
        warps.set("$name.YAW", location.yaw)
        warps.set("$name.PITCH", location.pitch)
        warps.set("$name.WORLD", location.world.name)
        warps.save()

        player.sendMessage(CC.translate("&aWarp set."))
    }
}