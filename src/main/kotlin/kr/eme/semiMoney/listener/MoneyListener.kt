package kr.eme.semiMoney.listener

import kr.eme.semiMoney.managers.FileManager
import kr.eme.semiMoney.managers.UserManager
import kr.eme.semiMoney.objects.User
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object MoneyListener : Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val player = e.player
        val uuid = player.uniqueId
        //첫 접속인지 확인
        var user = UserManager.getUser(uuid)
        //첫 접속일 경우 새로운 User 객체를 생성함.
        if (user == null) {
            user = User(uuid, player.name, 0)
            UserManager.addUser(user)
            FileManager.saveUserData(user) // 첫 접속 시 user_data.yml 파일에 추가
        } else {
            user.name = player.name //만약 닉네임이 변경되었을 시에는 업데이트를 해줌
        }
    }
}