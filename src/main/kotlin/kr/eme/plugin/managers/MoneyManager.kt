package kr.eme.plugin.managers

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
     * @param uuid player's UUID
     * @param amount
     * @return true or false
     */
    fun subtractMoney(uuid: UUID, amount: Int) : Boolean {
        val user = UserManager.getUser(uuid) ?: return false
        if (user.money < amount) return false
        user.money -= amount
        return true
    }
}