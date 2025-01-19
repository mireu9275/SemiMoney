package kr.eme.semiMoney.commands

import kr.eme.semiMoney.main
import kr.eme.semiMoney.managers.MoneyManager
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

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
        print(args.size)
        if (args.size != 2 || args[0].toIntOrNull() == null) {
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
            sender.sendMessage("$amount EP 수표를 $quantity 개 만큼 발행하였습니다.")
        } else {
            sender.sendMessage("소지금이 충분하지 않습니다.")
        }
        return true
    }

    private fun createChequeItem(amount: Int, quantity: Int): ItemStack {
        val cheque = ItemStack(Material.PAPER, quantity)
        val meta = cheque.itemMeta!!

        // 아이템 이름과 설명 설정
        meta.setDisplayName("§f[수표] §a$amount §fEP")
        meta.lore = listOf("§f우클릭으로 사용")

        // NBT 태그로 금액 저장
        val container = meta.persistentDataContainer
        val key = NamespacedKey(main, "cheque_amount")
        container.set(key, PersistentDataType.INTEGER, amount)

        // (선택) CustomModelData 설정
        meta.setCustomModelData(1234) // 수표를 나타내는 고유 CustomModelData

        cheque.itemMeta = meta
        return cheque
    }

}