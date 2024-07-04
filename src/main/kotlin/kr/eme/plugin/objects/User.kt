package kr.eme.plugin.objects

import java.util.*

data class User(
    val uuid: UUID,
    var name: String = "",
    var money: Int = 0
)
