package kr.eme.plugin;

import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

class SemiMoney: JavaPlugin() {
    val pluginManager: PluginManager = server.pluginManager
    override fun onEnable() {
        logger.info("Server enable!")
    }
    override fun onDisable() {
        logger.info("Server disable!")
    }
}
