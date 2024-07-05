package kr.eme.semiMoney.commands

import kr.eme.semiMoney.managers.MoneyManager
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

object ChequeCommand : CommandExecutor {
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
        if (args.isEmpty()) {
            sender.sendMessage("사용법: /수표 <금액> <수량>")
            return true
        }
        val amount = args[0].toIntOrNull()
        val quantity = args[1].toIntOrNull()

        if (amount == null) {
            sender.sendMessage("[금액] 을 입력해주세요!")
            return true
        }
        if (quantity == null) {
            sender.sendMessage("[수량] 을 입력해주세요!")
            return true
        }

        val uuid = sender.uniqueId
        val totalAmount = amount * quantity

        if (MoneyManager.subtractMoney(uuid, totalAmount)) {
            val cheque = createChequeItem(amount, quantity)
            sender.inventory.addItem(cheque)
            sender.sendMessage("$amount 원 수표를 $quantity 개 만큼 발행하였습니다.")
        } else {
            sender.sendMessage("소지금이 충분하지 않습니다.")
        }
        return true
    }

    private fun createChequeItem(amount: Int, quantity: Int): ItemStack {
        val cheque = ItemStack(Material.PAPER, quantity)
        val meta: ItemMeta = cheque.itemMeta!!
        meta.setDisplayName("[수표] $amount 원")
        meta.lore = listOf("우클릭 하여 사용")
        meta.setCustomModelData(amount)
        cheque.itemMeta = meta
        return cheque
    }
}