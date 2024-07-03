package kr.eme.plugin;

import kr.eme.plugin.commands.MoneyCommand
import kr.eme.plugin.listener.MoneyListener
import org.bukkit.plugin.java.JavaPlugin

class SemiMoney: JavaPlugin() {
    override fun onEnable() {
        //이벤트 리스너 등록
        server.pluginManager.registerEvents(MoneyListener, this)
        //커맨드 등록
        getCommand("MoneyCommand")?.setExecutor(MoneyCommand)
        logger.info("Server enable!")
    }
    override fun onDisable() {
        logger.info("Server disable!")
    }
}
