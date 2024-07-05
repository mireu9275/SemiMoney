package kr.eme.semiMoney;

import kr.eme.semiMoney.commands.ChequeCommand
import kr.eme.semiMoney.commands.MoneyCommand
import kr.eme.semiMoney.listener.ChequeListener
import kr.eme.semiMoney.listener.MoneyListener
import kr.eme.semiMoney.managers.FileManager
import kr.eme.semiMoney.managers.UserManager
import kr.eme.semiMoney.objects.User
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class SemiMoney: JavaPlugin() {
    override fun onEnable() {
        //FileManager 에 DataFolder 전달
        FileManager.init(dataFolder)
        //모든 User 의 정보를 불러옴
        UserManager.loadAllUsers()
        //이벤트 리스너 등록
        registerEvents()
        //커맨드 등록
        registerCommands()
        logger.info("Server enable!")
    }
    override fun onDisable() {
        UserManager.saveAllUsers()
        logger.info("Server disable!")
    }

    private fun registerEvents() {
        server.pluginManager.registerEvents(MoneyListener, this)
        server.pluginManager.registerEvents(ChequeListener, this)
    }

    private fun registerCommands() {
        getCommand("money")?.setExecutor(MoneyCommand)
        getCommand("cheque")?.setExecutor(ChequeCommand)
    }

    private fun test() {
        val user = User(uuid = UUID.randomUUID(), name = "TestPlayer1", money = 1000)
        FileManager.saveUserData(user)
    }

}
