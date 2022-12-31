package org.hyrical.warps.utils

import org.bukkit.ChatColor

object CC {
    fun translate(message: String): String {
        return ChatColor.translateAlternateColorCodes('&', message)
    }
}