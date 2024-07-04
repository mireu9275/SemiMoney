package kr.eme.plugin.commands

import kr.eme.plugin.managers.MoneyManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


object MoneyCommand : CommandExecutor {
    /**
     * On command
     *
     * 커맨드 입력 시
     * @param sender
     * @param command
     * @param label
     * @param args
     * @return
     */
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        //sender 가 플레이어가 아닐 경우...
        if (sender !is Player) {
            sender.sendMessage("콘솔에서는 이 명령어를 사용할 수 없습니다.")
            return true
        }
        //money 만 입력했을 경우...
        if (args.isEmpty()) {
            getUserMoney(sender)
            return true
        }
        return true
    }

    /**
     * Get user money
     *
     * @param player
     */
    private fun getUserMoney(player: Player) {
        val money = MoneyManager.getMoney(player.uniqueId) ?: 0
        player.sendMessage("${player.name} 님의 소지금은 $money 원 입니다.")
    }
}
