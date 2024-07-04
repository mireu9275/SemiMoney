package kr.eme.plugin.listener

import kr.eme.plugin.managers.MoneyManager
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

object ChequeListener : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.hand != EquipmentSlot.HAND) return
        val player = event.player
        val item: ItemStack = event.item ?: return

        if (item.type != Material.PAPER && item.itemMeta?.hasCustomModelData() == true) {
            val amount = item.itemMeta!!.customModelData
            val uuid = player.uniqueId

            MoneyManager.addMoney(uuid, amount)
            player.sendMessage("수표를 사용하여 $amount 원을 획득하였습니다.")

            item.amount -= 1
            if (item.amount <= 0) {
                player.inventory.remove(item)
            }
        }
    }
}