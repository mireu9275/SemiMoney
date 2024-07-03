package kr.eme.plugin.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player


object MoneyCommand : TabExecutor {
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
        if (sender !is Player) {
            sender.sendMessage("콘솔에서는 이 명령어를 사용할 수 없습니다.")
            return true
        }
        return true
    }

    /**
     * On tab complete
     *
     * 커맨드 입력 후 Tab 키를 눌렀을 시
     * @param sender
     * @param command
     * @param alias
     * @param args
     * @return
     */
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String>? {
        return if (args.size == 1) {
            mutableListOf("","","")
        } else {
            null
        }
    }
}
