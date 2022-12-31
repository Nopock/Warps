package org.hyrical.warps.config

import com.google.common.base.Preconditions
import org.bukkit.configuration.file.YamlConfiguration
import org.hyrical.warps.WarpsPlugin
import java.io.File


class WarpsFile : YamlConfiguration() {

    private val fileName = "warps.yml"
    private val file: File = File(WarpsPlugin.instance.dataFolder, fileName)

    init {
        Preconditions.checkArgument(!initialed, "Error, config file already initialed!")
        initialed = true
        if (!file.exists()) WarpsPlugin.instance.saveResource(fileName, false)
        this.reload()
        config = this
    }

    fun reload() {
        super.load(file)
        super.save(file)
    }

    fun save() {
        super.save(file)
    }

    companion object {
        private var config: WarpsFile? = null

        fun getConfig(): WarpsFile {
            if (config == null) {
                config = WarpsFile()
            }
            return config!!
        }

        private var initialed = false
            set(value) {
                Preconditions.checkArgument(value, "Shush, dont modify code :)")
                field = value
            }
    }
}
