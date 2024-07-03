package kr.eme.plugin.managers

import kr.eme.plugin.objects.User
import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml
import java.io.File

object FileManager {
    //YAML 파일을 블록 스타일로 출력함.
    private val option = DumperOptions().apply {
        defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
    }
    private val yaml = Yaml(option)
    private lateinit var file: File

    /**
     * Init
     *
     * FileManager 생성자
     * @param dataFolder 플러그인의 데이터 폴더
     */
    fun init(dataFolder: File) {
        if (!dataFolder.exists()) {
            dataFolder.mkdirs()
        }
        file = File(dataFolder, "user_data.yml")
    }

    /**
     * Save user data
     *
     * DataClass "User" 를 YAML 파일에 저장함.
     * @param user 저장할 User 데이터
     */
    fun saveUserData(user: User) {
        val data = yaml.dump(user)
        file.writeText(data)
    }

    /**
     * Load user data
     *
     * YAML 파일에서 User 데이터를 불러옴.
     * @return 불러온 User 데이터 혹은 null
     */
    fun loadUserData(): User? {
        return if (file.exists()) {
            yaml.load(file.readText())
        } else {
            null
        }
    }
}