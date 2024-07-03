package listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object MoneyListener : Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val player = e.player
        val uuid = player.uniqueId
    }
    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        val player = e.player
        val uuid = player.uniqueId
    }
}