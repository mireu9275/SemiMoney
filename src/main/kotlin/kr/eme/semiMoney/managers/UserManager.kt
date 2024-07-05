package kr.eme.semiMoney.managers

import kr.eme.semiMoney.objects.User
import java.util.*

object UserManager {
    private val userMap = HashMap<UUID, User>()

    /**
     * 유저 정보를 가져오는 함수
     * @param uuid 유저의 UUID
     * @return 유저 객체, 없으면 null 반환
     */
    fun getUser(uuid: UUID): User? = userMap[uuid]

    /**
     * 유저 정보를 추가하는 함수
     * @param user 추가할 유저 객체
     */
    fun addUser(user: User) {
        userMap[user.uuid] = user
    }

    /**
     * 유저 정보를 삭제하는 함수
     * @param uuid 삭제할 유저의 UUID
     */
    fun removeUser(uuid: UUID) {
        userMap.remove(uuid)
    }

    /**
     * 모든 유저 정보를 파일에서 로드하는 함수
     */
    fun loadAllUsers() {
        val users = FileManager.loadAllUsers()
        users.forEach { user ->
            userMap[user.uuid] = user
        }
    }

    /**
     * 모든 유저 정보를 파일에 저장하는 함수
     */
    fun saveAllUsers() {
        userMap.values.forEach { user ->
            FileManager.saveUserData(user)
        }
    }
}
