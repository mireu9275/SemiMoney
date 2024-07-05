package kr.eme.semiMoney.managers

import org.bukkit.Bukkit
import java.util.*

object MoneyManager {
    /**
     * Get money
     *
     * @param uuid player's UUID
     * @return user's money or null
     */
    fun getMoney(uuid: UUID): Int? {
        val user = UserManager.getUser(uuid)
        return user?.money
    }

    /**
     * Set money
     *
     * @param uuid player's UUID
     * @param amount
     * @return true or false
     */
    fun setMoney(uuid: UUID, amount: Int) : Boolean {
        val user = UserManager.getUser(uuid) ?: return false
        user.money = amount
        return true
    }

    /**
     * Add money
     *
     * 수표를 통하여만 작동하므로 무조건 이 구문이 실행될 시 플레이어는 온라인임
     * @param uuid player's UUID
     * @param amount
     * @return true or false
     */
    fun addMoney(uuid: UUID, amount: Int) : Boolean {
        val user = UserManager.getUser(uuid) ?: return false
        user.money += amount
        return true
    }

    /**
     * Subtract money
     *
     * 수표를 통하여만 작동하므로 무조건 이 구문이 실행될 시 플레이어는 온라인임
     * @param uuid player's UUID
     * @param amount
     * @return true or false
     */
    fun subtractMoney(uuid: UUID, amount: Int) : Boolean {
        val user = UserManager.getUser(uuid) ?: return false
        //UUID 를 통하여 플레이어를 검색함
        val player = Bukkit.getPlayer(uuid) ?: return false
        if (amount <= 0) {
            player.sendMessage("금액은 0보다 커야 합니다!")
            return false
        }
        if (user.money < amount) return false
        user.money -= amount
        return true
    }
}