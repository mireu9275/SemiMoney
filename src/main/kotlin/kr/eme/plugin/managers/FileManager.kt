package kr.eme.plugin.managers

import kr.eme.plugin.objects.User
import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.util.*

object FileManager {

    private val options = DumperOptions().apply {
        defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
    }

    private val yaml = Yaml(options)
    private lateinit var file: File

    /**
     * FileManager 초기화 함수
     * @param dataFolder 플러그인의 데이터 폴더
     */
    fun init(dataFolder: File) {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs() // 데이터 폴더가 없으면 생성
        }
        file = File(dataFolder, "user_data.yml")
    }

    /**
     * 사용자 데이터를 YAML 파일에 저장하는 함수
     * @param user 저장할 사용자 데이터
     */
    fun saveUserData(user: User) {
        val users = loadAllUsers().toMutableList()
        users.add(user)
        val data = users.map { userToMap(it) }
        file.writeText(yaml.dump(data))
    }

    /**
     * YAML 파일에서 모든 사용자 데이터를 불러오는 함수
     * @return 불러온 사용자 데이터 목록
     */
    fun loadAllUsers(): List<User> {
        return if (file.exists()) {
            val data = yaml.load<List<Map<String, Any>>>(file.readText())
            data.map { mapToUser(it) }
        } else {
            emptyList()
        }
    }

    /**
     * User 객체를 Map 형태로 변환하는 함수
     * @param user 변환할 User 객체
     * @return 변환된 Map
     */
    private fun userToMap(user: User): Map<String, Any> {
        return mapOf(
            "uuid" to user.uuid.toString(),
            "name" to user.name,
            "money" to user.money
        )
    }

    /**
     * Map 을 User 객체로 변환하는 함수
     * @param map 변환할 Map
     * @return 변환된 User 객체
     */
    private fun mapToUser(map: Map<String, Any>): User {
        return User(
            uuid = UUID.fromString(map["uuid"] as String),
            name = map["name"] as String,
            money = map["money"] as Int
        )
    }
}
