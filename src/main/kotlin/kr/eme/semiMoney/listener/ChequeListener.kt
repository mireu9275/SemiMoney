package kr.eme.semiMoney.listener

import kr.eme.semiMoney.main
import kr.eme.semiMoney.managers.MoneyManager
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

object ChequeListener: Listener {
@EventHandler
fun onPlayerInteract(event: PlayerInteractEvent) {
    if (event.hand != EquipmentSlot.HAND) return
    val player = event.player
    val item: ItemStack = event.item ?: return

    if (item.type == Material.PAPER) {
        val meta = item.itemMeta ?: return

        // CustomModelData 확인 (선택 사항)
        if (meta.customModelData != 1234) return

        // NBT 태그 확인
        val container = meta.persistentDataContainer
        val key = NamespacedKey(main, "cheque_amount")
        if (!container.has(key, PersistentDataType.INTEGER)) return

        // 금액 추출
        val amount = container.get(key, PersistentDataType.INTEGER) ?: return
        MoneyManager.addMoney(player.uniqueId, amount)

        player.sendMessage("§f[수표] §a$amount §fEP를 사용하여 금액을 획득하였습니다.")

        // 아이템 수량 감소
        item.amount -= 1
        if (item.amount <= 0) {
            player.inventory.remove(item)
        }
    }
}
    }
